package cz.muni.fi.pa165.musiclib.mvc.controllers;

import cz.muni.fi.pa165.musiclib.dto.GenreDTO;
import cz.muni.fi.pa165.musiclib.facade.GenreFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 
 *
 * @author
 */
@Controller
@RequestMapping(value = {"/genre"})
public class GenreController {

    final static Logger log = LoggerFactory.getLogger(GenreController.class);
    
    @Inject
    private MessageSource messageSource;
    
    @Inject
    private GenreFacade genreFacade;

    @RequestMapping(value = {"", "/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        log.debug("getGenres()");
        model.addAttribute("genres", genreFacade.getAllGenres());
        return "genre/index";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model){
        log.debug("create genre()");
        model.addAttribute("genreCreate", new GenreDTO());
        return "genre/create";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(
            @Valid @ModelAttribute("genreCreate") GenreDTO genreFormBean,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriComponentsBuilder) {
        
        log.debug("create genre(formBean={})", genreFormBean);

        if (bindingResult.hasErrors()) {
            for (ObjectError globalError : bindingResult.getGlobalErrors()) {
                log.error("ObjectError: {}", globalError);
            }
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                model.addAttribute(fieldError.getField() + "_error", true);
                log.error("FieldError: {}", fieldError);
            }

            return "/genre/create";
        }
        
        Long id = genreFacade.create(genreFormBean);        
        
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Genre with id " + id + " created");
        return "redirect:" + uriComponentsBuilder.path("/genre").build().encode().toUriString();
    }
}
