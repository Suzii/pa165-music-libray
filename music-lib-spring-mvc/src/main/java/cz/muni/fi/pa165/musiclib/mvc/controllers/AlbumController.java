package cz.muni.fi.pa165.musiclib.mvc.controllers;

import cz.muni.fi.pa165.musiclib.dto.AlbumChangeAlbumArtDTO;
import cz.muni.fi.pa165.musiclib.dto.AlbumDTO;
import cz.muni.fi.pa165.musiclib.dto.SongDTO;
import cz.muni.fi.pa165.musiclib.facade.AlbumFacade;
import cz.muni.fi.pa165.musiclib.facade.SongFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Album controller
 *
 * @author Martin Stefanko
 * @version 12/12/2015
 */
@Controller
@RequestMapping(value = {"/album"})
public class AlbumController extends BaseController {

    final static Logger log = LoggerFactory.getLogger(AlbumController.class);

    @Inject
    private AlbumFacade albumFacade;

    @Inject
    private SongFacade songFacade;

    @Inject
    private MessageSource messageSource;

    @RequestMapping(value = {"", "/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        List<AlbumDTO> albums = albumFacade.getAllAlbums();
        model.addAttribute("albums", albums);

        return "album/index";
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public String create(Model model) {

        model.addAttribute("albumCreate", new AlbumDTO());
        return "album/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(
            //DO NOT CHANGE the order of first two parameters
            @Valid @ModelAttribute("albumCreate") AlbumDTO formBean,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriComponentsBuilder,
            HttpServletRequest request) throws IOException, ServletException {

        log.debug("create(formBean={})", formBean);

        //if there are any validation errors forward back to the the form
        if (bindingResult.hasErrors()) {
            addValidationErrors(bindingResult, model);
            return "/album/create";
        }

        Long albumId = albumFacade.createAlbum(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Album with name " + formBean.getTitle() + " created");

        //------------------
        log.error("AlbumArt: " + Arrays.toString(formBean.getAlbumArt()));
        log.error("21: " + Arrays.toString(albumFacade.getAlbumById(2L).getAlbumArt()));

        return "redirect:" + uriComponentsBuilder.path("/album/detail/{id}").buildAndExpand(albumId).encode().toUriString();
    }

    @RequestMapping("/albumImage/{id}")
    public void albumImage(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        AlbumDTO albumDTO = albumFacade.getAlbumById(id);
        byte[] image = albumDTO.getAlbumArt();
        if (image == null) {
            response.sendRedirect(request.getContextPath() + "/no-image.png");
        } else {
            response.setContentType(albumDTO.getAlbumArtMimeType());
            ServletOutputStream out = response.getOutputStream();
            out.write(image);
            out.flush();
        }
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model) {

        AlbumDTO albumDTO = albumFacade.getAlbumById(id);

        model.addAttribute("album", albumDTO);
        return "album/detail";
    }

    /**
     * Lists all songs associated with album.
     *
     * @param albumId
     * @param model
     * @return
     */
    @RequestMapping(value = {"/songs/{albumId}"}, method = RequestMethod.GET)
    public String songs(@PathVariable long albumId, Model model) {

        AlbumDTO albumDTO = albumFacade.getAlbumById(albumId);
        model.addAttribute("album", albumDTO);

        List<SongDTO> songs = songFacade.findByAlbum(albumId);
        model.addAttribute("songs", songs);

        return "album/albumSongs";
    }

    @RequestMapping(value = {"/update/{id}"}, method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {

        AlbumDTO album = albumFacade.getAlbumById(id);

        model.addAttribute("albumUpdate", album);
        return "album/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(
            //DO NOT CHANGE the order of first two parameters
            @Valid @ModelAttribute("albumUpdate") AlbumDTO formBean,
            BindingResult bindingResult,
            @PathVariable long id,
            Model model,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriComponentsBuilder) {

        log.debug("albumController.update(formBean={})", formBean);

        if (bindingResult.hasErrors()) {
            addValidationErrors(bindingResult, model);
            return "redirect:" + uriComponentsBuilder.path("/album/update/{id}").buildAndExpand(id).encode().toUriString();
        }

        albumFacade.update(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Album " + formBean.getTitle() + " updated");
        return "redirect:" + uriComponentsBuilder.path("/album/detail/{id}").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
    public String remove(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {

        log.debug("albumController.remove() with id = " + id);

        AlbumDTO albumDTO = albumFacade.getAlbumById(id);

        albumFacade.deleteAlbum(id);

        redirectAttributes.addFlashAttribute("alert_success", "Album " + albumDTO.getTitle() + " successfully deleted.");
        return "redirect:" + uriBuilder.path("/album").toUriString();
    }

    @RequestMapping(value = "/changeImage/{id}", method = RequestMethod.GET)
    public String changeImage(@PathVariable long id, Model model) {

        AlbumDTO albumDTO = albumFacade.getAlbumById(id);

        model.addAttribute("album", albumDTO);
        return "album/image";
    }
    
    @RequestMapping(value = "/upload/{id}", method = RequestMethod.POST)
    public String handleFileUpload(@PathVariable long id,
                                   @RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,
                                   UriComponentsBuilder uriComponentsBuilder) throws IOException {
        byte[] bytes = file.getBytes();

        AlbumChangeAlbumArtDTO albumDTO = new AlbumChangeAlbumArtDTO();
        albumDTO.setAlbumId(id);
        albumDTO.setImage(bytes);
        albumDTO.setMimeType("image/jpeg");

        albumFacade.changeAlbumArt(albumDTO);

        redirectAttributes.addFlashAttribute("alert_success", "Album updated");
        return "redirect:" + uriComponentsBuilder.path("/album/detail/{id}").buildAndExpand(id).encode().toUriString();
    }

}
