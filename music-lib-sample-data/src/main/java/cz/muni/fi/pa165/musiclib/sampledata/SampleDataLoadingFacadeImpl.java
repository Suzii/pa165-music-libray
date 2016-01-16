package cz.muni.fi.pa165.musiclib.sampledata;

import cz.muni.fi.pa165.musiclib.entity.Album;
import cz.muni.fi.pa165.musiclib.entity.Genre;
import cz.muni.fi.pa165.musiclib.entity.Musician;
import cz.muni.fi.pa165.musiclib.entity.Song;
import cz.muni.fi.pa165.musiclib.entity.User;
import cz.muni.fi.pa165.musiclib.enums.Sex;
import cz.muni.fi.pa165.musiclib.service.AlbumService;
import cz.muni.fi.pa165.musiclib.service.GenreService;
import cz.muni.fi.pa165.musiclib.service.MusicianService;
import cz.muni.fi.pa165.musiclib.service.SongService;
import cz.muni.fi.pa165.musiclib.service.UserService;
import cz.muni.fi.pa165.musiclib.utils.AlbumBuilder;
import cz.muni.fi.pa165.musiclib.utils.GenreBuilder;
import cz.muni.fi.pa165.musiclib.utils.MusicianBuilder;
import cz.muni.fi.pa165.musiclib.utils.SongBuilder;
import cz.muni.fi.pa165.musiclib.utils.UserBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Creates and populate the database with sample data.
 *
 * @author xstefank (422697@mail.muni.cz)
 * @version 12/5/15
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    private static final Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);
    public static final String JPEG = "image/jpeg";

    @Inject
    private AlbumService albumService;

    @Inject
    private SongService songService;

    @Inject
    private MusicianService musicianService;

    @Inject
    private GenreService genreService;

    @Inject
    private UserService userService;

    private final AlbumBuilder albumBuilder = new AlbumBuilder();
    private final SongBuilder songBuilder = new SongBuilder();
    private final MusicianBuilder musicianBuilder = new MusicianBuilder();
    private final GenreBuilder genreBuilder = new GenreBuilder();
    private final UserBuilder userBuilder = new UserBuilder();

    Map<String, Genre> genres = new HashMap<String, Genre>();
    Map<String, Album> albums = new HashMap<String, Album>();
    Map<String, Musician> musicians = new HashMap<String, Musician>();
    
    @Override
    public void loadData() throws IOException {
        
        createUsers();
                
        createGenres();
        
        createMusicians();

        createAlbums();
        
        createSongs();
    }

    private void createSongs() {
        Album album = albums.get("21");
        Musician artist = musicians.get("adele");
        Genre genre = genres.get("pop");        
        
        song("Rolling in the Deep", "Best song 2013", 1, 1, album, artist, genre, "rYEDA3JcQqw");
        song("Roumor Has It", "", 2, 1, album, artist, genre, "bgpInZ6OQ40");
        song("Turning Tables", "", 3, 1, album, artist, genre, "bsFCO8-oCEQ");
        song("Don't You Remember", "", 4, 1, album, artist, genre);
        song("Set Fire to the Rain", "", 5, 1, album, artist, genre, "FlsBObg-1BQ");
        song("He Won't Go", "", 6, 1, album, artist, genre);
        song("Take It All", "", 7, 1, album, artist, genre);
        song("I'll be Waiting", "", 8, 1, album, artist, genre);
        song("One and Only", "", 9, 1, album, artist, genre);
        song("Love Song", "", 10, 1, album, artist, genre);
        song("Someone Like You", "Ghoosebumps", 11, 1, album, artist, genre, "hLQl3WQQoQ0");
        
        album = albums.get("jukebox");
        artist = musicians.get("brunoMars");
        song("Young Girls", "Love song lvl.100", 1, 1, album, artist, genre, "CyM5AjiZris");
        song("Locked out of Heaven", "", 2, 1, album, artist, genre, "e-fA-gBCkj0");
        song("Gorilla", "", 3, 1, album, artist, genre);
        song("Treasure", "", 4, 1, album, artist, genre, "nPvuNsRccVw");
        song("Moonshine", "", 5, 1, album, artist, genre);
        song("When I Was Your Man", "", 6, 1, album, artist, genre, "ekzHIouo8Q4");
        song("Natalie", "", 7, 1, album, artist, genre);
        song("Show Me", "", 8, 1, album, artist, genre);
        song("Money Make Her Smile", "", 9, 1, album, artist, genre);
        song("If I knew", "", 10, 1, album, artist, genre);
        
        album = albums.get("hozier");
        artist = musicians.get("hozier");
        genre = genres.get("rock");
        song("Take me to church", "Totaly crazy about this song", 1, 1, album, artist, genre);
        
        album = albums.get("blackSands");
        artist = musicians.get("bonobo");
        genre = genres.get("electro");
        song("Prelude", "", 1, 1, album, artist, genre, "GezIBBJkhME");
        song("Kiara", "", 2, 1, album, artist, genre, "L-kyRh7N-kE");
        song("Kong", "", 3, 1, album, artist, genre, "lZbgyKJkHxQ");
        song("Eyesdown", "", 4, 1, album, artist, genre, "-6bbM5HZz5c");
        song("We Could Forever", "", 5, 1, album, artist, genre, "urCLo4vNpzs");
        song("1009", "", 6, 1, album, artist, genre, "2K_2LTLb5tI");
        song("All in Forms", "", 7, 1, album, artist, genre, "9W-epjHQDZs");
        song("The Keeper", "", 8, 1, album, artist, genre, "Q7Y4sPkTDbE");
        song("Stay the Same", "", 9, 1, album, artist, genre, "TsxQQuIrfhQ");
        song("Animals", "", 10, 1, album, artist, genre, "Q8RzwkyhYCg");
        song("Black Sands", "", 11, 1, album, artist, genre, "cTjF2_-bneM");


        album = albums.get("electTheDead");
        artist = musicians.get("serjTankian");
        genre = genres.get("metal");
        song("Empty Walls", "2-track promo", 1, 1, album, artist, genre, "-CxKA1uETxE");
        song("The Unthinking Majority", "2-track promo", 2, 1, album, artist, genre, "WQeRG72E3OM");
        song("Money", "", 3, 1, album, artist, genre, "51jYvbcrHKs");
        song("Feed Us", "", 4, 1, album, artist, genre, "TOxnuQlF0d0");
        song("Saving Us", "", 5, 1, album, artist, genre, "9Wk38bW8whc");
        song("Sky Is Over", "", 6, 1, album, artist, genre, "GY9kQcWLvEM");
        song("Baby", "", 7, 1, album, artist, genre, "P-6FLQliz84");
        song("Honking Antelope", "", 8, 1, album, artist, genre, "XLTUOYEvddw");
        song("Lie Lie Lie", "", 9, 1, album, artist, genre, "TkPbJIlEXow");
        song("Praise the Lord and Pass the Ammunition", "", 10, 1, album, artist, genre, "buX2OoPrJvc");
        song("Beethoven's Cunt", "", 11, 1, album, artist, genre, "9FPm1aDLmWA");
        song("Elect the Dead", "", 12, 1, album, artist, genre, "PX1qZ6seZUQ");

        
        album = albums.get("encore");
        artist = musicians.get("eminem");
        genre = genres.get("rap");
        song("Curtains Up", "", 1, 1, album, artist, genre, "");
        song("Evil Deeds", "", 2, 1, album, artist, genre, "");
        song("Never Enough", "", 3, 1, album, artist, genre, "");
        song("Yellow Brick Road", "", 4, 1, album, artist, genre, "");
        song("Like Toy Soldiers", "", 5, 1, album, artist, genre, "");
        song("Mosh", "", 6, 1, album, artist, genre, "");
        song("Puke", "", 7, 1, album, artist, genre, "");
        song("My 1st Single", "", 8, 1, album, artist, genre, "");
        song("Paul", "", 9, 1, album, artist, genre, "");
        song("Rain Man", "", 10, 1, album, artist, genre, "");
        song("Big Weenie", "", 11, 1, album, artist, genre, "");
        song("Em Calls Paul", "", 12, 1, album, artist, genre, "");
        song("Just Lose It", "", 13, 1, album, artist, genre, "");
        song("Ass Like That", "", 14, 1, album, artist, genre, "");
        song("Spend Some Time", "", 15, 1, album, artist, genre, "");
        song("Mockingbird", "", 16, 1, album, artist, genre, "");
        song("Crazy in Love", "", 17, 1, album, artist, genre, "");
        song("One Shot 2 Shot", "", 18, 1, album, artist, genre, "");
        song("Final Thought", "", 19, 1, album, artist, genre, "");
        song("Encore / Curtains Down", "", 20, 1, album, artist, genre, "");
        
        
        album = albums.get("relapse");
        artist = musicians.get("eminem");
        genre = genres.get("rap");
        song("Dr. West (Skit) (feat. Dominic West)", "",  1 , 1, album, artist, genre, "");
        song("3 a.m.", "",  2 , 1, album, artist, genre, "");
        song("My Mom", "",  3 , 1, album, artist, genre, "");
        song("Insane", "",  4 , 1, album, artist, genre, "");
        song("Bagpipes From Baghdad", "",  5 , 1, album, artist, genre, "");
        song("Hello", "",  6 , 1, album, artist, genre, "");
        song("Tonya (Skit) (feat. Elizabeth Keener)", "",  7 , 1, album, artist, genre, "");
        song("Same Song & Dance", "",  8 , 1, album, artist, genre, "");
        song("We Made You (feat. Charmagne Tripp)", "",  9 , 1, album, artist, genre, "");
        song("Medicine Ball", "",  10, 1, album, artist, genre, "");
        song("Paul (Skit) (feat. Paul Rosenberg)", "",  11, 1, album, artist, genre, "");
        song("Stay Wide Awake", "",  12, 1, album, artist, genre, "");
        song("Old Time's Sake (feat. Dr. Dre)", "",  13, 1, album, artist, genre, "");
        song("Must Be The Ganja", "",  14, 1, album, artist, genre, "");
        song("Mr. Mathers (Skit) (feat. Elizabeth Keener & Matthew St. Patrick)", "", 15, 1, album, artist, genre, "");
        song("Déjà Vu", "",  16, 1, album, artist, genre, "");
        song("Beautiful", "",  17, 1, album, artist, genre, "");
        song("Crack A Bottle (feat. Dr. Dre & 50 Cent)", "",  18, 1, album, artist, genre, "");
        song("Steve Berman (Skit) (feat. Steve Berman & Angela Yee)", "",  19, 1, album, artist, genre, "");
        song("Underground/Ken Kaniff", "",  20, 1, album, artist, genre, "");
        song("My Darling (Deluxe Edition Bonus Track)", "",  21, 1, album, artist, genre, "");
        song("Careful What You Wish For (Deluxe Edition Bonus Track)", "",  22, 1, album, artist, genre, "");
        
        
        album = albums.get("recovery");
        artist = musicians.get("eminem");
        genre = genres.get("rap");
        song("Cold Wind Blows", "", 1 , 1, album, artist, genre, "");
        song("Talkin' 2 Myself (feat. Kobe)", "", 2 , 1, album, artist, genre, "");
        song("On Fire", "", 3 , 1, album, artist, genre, "");
        song("Won't Back Down (feat. Pink)", "", 4 , 1, album, artist, genre, "");
        song("W.T.P. (White Trash Party)", "", 5 , 1, album, artist, genre, "");
        song("Going Through Changes", "", 6 , 1, album, artist, genre, "");
        song("Not Afraid", "", 7 , 1, album, artist, genre, "");
        song("Seduction", "", 8 , 1, album, artist, genre, "");
        song("No Love (feat. Lil Wayne)", "", 9 , 1, album, artist, genre, "");
        song("Space Bound", "", 10, 1, album, artist, genre, "");
        song("Cinderella Man", "", 11, 1, album, artist, genre, "");
        song("25 to Life", "", 12, 1, album, artist, genre, "");
        song("So Bad", "", 13, 1, album, artist, genre, "");
        song("Almost Famous", "", 14, 1, album, artist, genre, "");
        song("Love the Way You Lie", "", 15, 1, album, artist, genre, "");
        song("You're Never Over", "", 16, 1, album, artist, genre, "");
        song("Untitled (Hidden Track)", "", 17, 1, album, artist, genre, "");
        
        
        album = albums.get("cheese");
        artist = musicians.get("stromae");
        genre = genres.get("electro");
        song("Bienvenue chez moi ", "", 1 , 1, album, artist, genre, "");
        song("Te Quiero ", "", 2 , 1, album, artist, genre, "");
        song("Peace or Violence ", "", 3 , 1, album, artist, genre, "");
        song("Rail de musique ", "", 4 , 1, album, artist, genre, "");
        song("Alors on danse ", "", 5 , 1, album, artist, genre, "");
        song("Summertime ", "", 6 , 1, album, artist, genre, "");
        song("Dodo ", "", 7 , 1, album, artist, genre, "");
        song("Silence ", "", 8 , 1, album, artist, genre, "");
        song("Je Cours ", "", 9 , 1, album, artist, genre, "");
        song("House'llelujah ", "", 10, 1, album, artist, genre, "");
        song("Cheese ", "", 11, 1, album, artist, genre, "");
        
        
        album = albums.get("racineCarree");
        artist = musicians.get("stromae");
        genre = genres.get("electro");
        song("Ta fête", "", 1 , 1, album, artist, genre, "");
        song("Papaoutai", "", 2 , 1, album, artist, genre, "");
        song("Bâtard 	", "", 3 , 1, album, artist, genre, "");
        song("Ave Cesaria 	", "", 4 , 1, album, artist, genre, "");
        song("Tous les mêmes 	", "", 5 , 1, album, artist, genre, "");
        song("Formidable 	", "", 6 , 1, album, artist, genre, "");
        song("Moules frites 	", "", 7 , 1, album, artist, genre, "");
        song("Carmen 	", "", 8 , 1, album, artist, genre, "");
        song("Humain à l'eau 	", "", 9 , 1, album, artist, genre, "");
        song("Quand c'est? 	", "", 10, 1, album, artist, genre, "");
        song("Sommeil 	", "", 11, 1, album, artist, genre, "");
        song("Merci 	", "", 12, 1, album, artist, genre, "");
        song("AVF (featuring Maître Gims and Orelsan)", "", 13, 1, album, artist, genre, "");
        song("Papaoutai (featuring Angel Haze)", "", 14, 1, album, artist, genre, "");
        
        
        album = albums.get("lights");
        artist = musicians.get("ellieGoulding");
        genre = genres.get("pop");
        song("Guns and Horses  ", "", 1 , 1, album, artist, genre, "");
        song("Starry Eyed  ", "", 2 , 1, album, artist, genre, "");
        song("This Love (Will Be Your Downfall)  ", "", 3 , 1, album, artist, genre, "");
        song("Under the Sheets  ", "", 4 , 1, album, artist, genre, "");
        song("The Writer  ", "", 5 , 1, album, artist, genre, "");
        song("Every Time You Go  ", "", 6 , 1, album, artist, genre, "");
        song("Wish I Stayed  ", "", 7 , 1, album, artist, genre, "");
        song("Your Biggest Mistake  ", "", 8 , 1, album, artist, genre, "");
        song("I’ll Hold My Breath  ", "", 9 , 1, album, artist, genre, "");
        song("Salt Skin  ", "", 10, 1, album, artist, genre, "");

        
        album = albums.get("thriller");
        artist = musicians.get("michaelJackson");
        genre = genres.get("pop");
        song("Wanna Be Startin' Somethin'", "", 1 , 1, album, artist, genre, "");
        song("Baby Be Mine", "", 2 , 1, album, artist, genre, "");
        song("The Girl Is Mine", "", 3 , 1, album, artist, genre, "");
        song("Thriller", "", 4 , 1, album, artist, genre, "");
        song("Beat It", "", 5 , 1, album, artist, genre, "");
        song("Billie Jean", "", 6 , 1, album, artist, genre, "");
        song("Human Nature", "", 7 , 1, album, artist, genre, "");
        song("P.Y.T. (Pretty Young Thing)", "", 8 , 1, album, artist, genre, "");
        song("The Lady in My Life", "", 9 , 1, album, artist, genre, "");

        
        album = albums.get("bad");
        artist = musicians.get("michaelJackson");
        genre = genres.get("pop");
        song("Bad", "",  1 , 1, album, artist, genre, "");
        song("The Way You Make Me Feel  ", "", 2 , 1, album, artist, genre, "");
        song("Speed Demon  ", "", 3 , 1, album, artist, genre, "");
        song("Liberian Girl  ", "", 4 , 1, album, artist, genre, "");
        song("Just Good Friends  ", "", 5 , 1, album, artist, genre, "");
        song("Another Part of Me  ", "", 6 , 1, album, artist, genre, "");
        song("Man in the Mirror  ", "", 7 , 1, album, artist, genre, "");
        song("I Just Can't Stop Loving You  ", "",8 , 1, album, artist, genre, "");
        song("Dirty Diana  ", "", 9 , 1, album, artist, genre, "");
        song("Smooth Criminal  ", "", 10, 1, album, artist, genre, "");
        song("Leave Me Alone", "", 11, 1, album, artist, genre, "");
        
        album = albums.get("dangerous");
        artist = musicians.get("michaelJackson");
        genre = genres.get("rnb");
        song("Jam  ", "", 1 , 1, album, artist, genre, "");
        song("Why You Wanna Trip on Me  ", "", 2 , 1, album, artist, genre, "");
        song("In the Closet  ", "", 3 , 1, album, artist, genre, "");
        song("She Drives Me Wild  ", "", 4 , 1, album, artist, genre, "");
        song("Remember the Time  ", "", 5 , 1, album, artist, genre, "");
        song("Can't Let Her Get Away  ", "", 6 , 1, album, artist, genre, "");
        song("Heal the World  ", "", 7 , 1, album, artist, genre, "");
        song("Black or White  ", "", 8 , 1, album, artist, genre, "");
        song("Who Is It  ", "", 9 , 1, album, artist, genre, "");
        song("Give In to Me  ", "", 10, 1, album, artist, genre, "");
        song("Will You Be There  ", "", 11, 1, album, artist, genre, "");
        song("Keep the Faith  ", "", 12, 1, album, artist, genre, "");
        song("Gone Too Soon  ", "", 13, 1, album, artist, genre, "");
        song("Dangerous  ", "", 14, 1, album, artist, genre, "");
        
        album = albums.get("selahSue");
        artist = musicians.get("selahSue");
        genre = genres.get("rnb");
        song("Title", "", 1 , 1, album, artist, genre, "");
        song("This World  ", "", 2 , 1, album, artist, genre, "");
        song("Peace of Mind  ", "", 3 , 1, album, artist, genre, "");
        song("Raggamuffin  ", "", 4 , 1, album, artist, genre, "");
        song("Crazy Vibes  ", "", 5 , 1, album, artist, genre, "");
        song("Black Part Love  ", "", 6 , 1, album, artist, genre, "");
        song("Mommy  ", "", 7 , 1, album, artist, genre, "");
        song("Explanations  ", "", 8 , 1, album, artist, genre, "");
        song("Please (feat. Cee Lo Green)", "", 9 , 1, album, artist, genre, "");
        song("Summertime  ", "", 10, 1, album, artist, genre, "");
        song("Crazy Sufferin Style  ", "", 11, 1, album, artist, genre, "");
        song("Fyah Fyah  ", "", 12, 1, album, artist, genre, "");
        song("Just Because I Do  ", "", 13, 1, album, artist, genre, "");
        song("I Truly Loved Ya", "", 14, 1, album, artist, genre, "");
        
        album = albums.get("clapton");
        artist = musicians.get("ericClapton");
        genre = genres.get("blues");
        song("Travelin' Alone", "", 1 , 1, album, artist, genre, "");
        song("Rockin' Chair", "", 2 , 1, album, artist, genre, "");
        song("River Runs Deep", "", 3 , 1, album, artist, genre, "");
        song("Judgement Day", "", 4 , 1, album, artist, genre, "");
        song("How Deep Is the Ocean?", "", 5 , 1, album, artist, genre, "");
        song("My Very Good Friend the Milkman", "", 6 , 1, album, artist, genre, "");
        song("Can't Hold Out Much Longer", "", 7 , 1, album, artist, genre, "");
        song("That's No Way to Get Along", "", 8 , 1, album, artist, genre, "");
        song("Everything Will Be Alright", "", 9 , 1, album, artist, genre, "");
        song("Diamonds Made from Rain", "", 10, 1, album, artist, genre, "");
        song("When Somebody Thinks You're Wonderful", "", 11, 1, album, artist, genre, "");
        song("Hard Times Blues", "", 12, 1, album, artist, genre, "");
        song("Run Back to Your Side", "", 13, 1, album, artist, genre, "");
        song("Autumn Leaves", "", 14, 1, album, artist, genre, "");
    }

    private void createAlbums() throws IOException {
        Calendar cal = new GregorianCalendar(2015, Calendar.JANUARY, 1);
        cal.set(2012, Calendar.DECEMBER, 7);
        albums.put("jukebox", album("Jukebox", "jukebox comment", cal.getTime(), readImage("jukebox.jpg"), JPEG, new ArrayList<Song>()));
        
        cal.set(2011, Calendar.JANUARY, 19);
        albums.put("21", album("21", "the best comments are empty comments", cal.getTime(), readImage("21.jpg"), JPEG, new ArrayList<Song>()));
        
        cal.set(2014, Calendar.FEBRUARY, 15);
        albums.put("hozier", album("Hozier", "", cal.getTime(), readImage("hozier.jpg"), JPEG, new ArrayList<Song>()));
        
        cal.set(2010, Calendar.MARCH, 29);
        albums.put("blackSands", album("Black Sands", "just chill :)", cal.getTime(), readImage("blackSands.jpg"), JPEG, new ArrayList<Song>()));

        cal.set(2007, Calendar.OCTOBER, 22);
        albums.put("electTheDead", album("Elect the Dead", "debut album", cal.getTime(), readImage("electTheDead.jpg"), JPEG, new ArrayList<Song>()));

        cal.set(2004, Calendar.NOVEMBER, 12);
        albums.put("encore", album("Encore", "Fifth studio album", cal.getTime(), readImage("encore.jpg"), JPEG, new ArrayList<Song>()));

        cal.set(2009, Calendar.MAY, 19);
        albums.put("relapse", album("Relapse", "Sixth studio album", cal.getTime(), readImage("relapse.jpg"), JPEG, new ArrayList<Song>()));

        cal.set(2010, Calendar.JUNE, 18);
        albums.put("recovery", album("Recovery", "Seventh studio album", cal.getTime(), readImage("recovery.jpg"), JPEG, new ArrayList<Song>()));

        cal.set(2010, Calendar.JUNE, 14);
        albums.put("cheese", album("Cheese", "Debut album", cal.getTime(), readImage("cheese.jpg"), JPEG, new ArrayList<Song>()));

        cal.set(2013, Calendar.AUGUST, 16);
        albums.put("racineCarree", album("Racine Carrée", "Second studio album", cal.getTime(), readImage("racineCarree.jpg"), JPEG, new ArrayList<Song>()));

        cal.set(2010, Calendar.FEBRUARY, 26);
        albums.put("lights", album("Lights ", "Debut album", cal.getTime(), readImage("lights.jpg"), JPEG, new ArrayList<Song>()));

        cal.set(1982, Calendar.NOVEMBER, 30);
        albums.put("thriller", album("Thriller", "Sixth studio album", cal.getTime(), readImage("thriller.jpg"), JPEG, new ArrayList<Song>()));

        cal.set(1987, Calendar.AUGUST, 31);
        albums.put("bad", album("Bad", "Seventh studio album", cal.getTime(), readImage("bad.jpg"), JPEG, new ArrayList<Song>()));

        cal.set(1991, Calendar.NOVEMBER, 26);
        albums.put("dangerous", album("Dangerous", "Eighth studio album", cal.getTime(), readImage("dangerous.jpg"), JPEG, new ArrayList<Song>()));

        cal.set(2011, Calendar.MARCH, 4);
        albums.put("selahSue", album("Selah sue", "Debut album", cal.getTime(), readImage("selahSue.jpg"), JPEG, new ArrayList<Song>()));

        cal.set(2010, Calendar.SEPTEMBER, 27);
        albums.put("clapton", album("Clapton", "Twentieth studio album", cal.getTime(), readImage("clapton.jpg"), JPEG, new ArrayList<Song>()));

        
        log.info("Music library albums loaded.");
    }

    private void createMusicians() {
        Calendar cal = new GregorianCalendar(2015, Calendar.JANUARY, 1);
        cal.set(1988, Calendar.MAY, 5);
        musicians.put("adele", musician("Adele", new ArrayList<Song>(), Sex.FEMALE, cal.getTime()));
        
        cal.set(1985, Calendar.OCTOBER, 8);
        musicians.put("brunoMars", musician("Bruno Mars", new ArrayList<Song>(), Sex.MALE, cal.getTime()));
        
        cal.set(1990, Calendar.MARCH, 17);
        musicians.put("hozier", musician("Hozier", new ArrayList<Song>(), Sex.FEMALE, cal.getTime()));
        
        cal.set(1976, Calendar.MARCH, 30);
        musicians.put("bonobo", musician("Bonobo", new ArrayList<Song>(), Sex.MALE, cal.getTime()));

        cal.set(1967, Calendar.AUGUST, 21);
        musicians.put("serjTankian", musician("Serj Tankian", new ArrayList<Song>(), Sex.MALE, cal.getTime()));
        
        cal.set(1972, Calendar.OCTOBER, 17);
        musicians.put("eminem", musician("Eminem", new ArrayList<Song>(), Sex.MALE, cal.getTime()));

        cal.set(1985, Calendar.MARCH, 12);
        musicians.put("stromae", musician("Stromae", new ArrayList<Song>(), Sex.MALE, cal.getTime()));

        cal.set(1986, Calendar.DECEMBER, 30);
        musicians.put("ellieGoulding", musician("Ellie Goulding", new ArrayList<Song>(), Sex.FEMALE, cal.getTime()));

        cal.set(1958, Calendar.AUGUST, 29);
        musicians.put("michaelJackson", musician("Michael Jackson", new ArrayList<Song>(), Sex.MALE, cal.getTime()));

        cal.set(1989, Calendar.MAY, 3);
        musicians.put("selahSue", musician("Selah Sue", new ArrayList<Song>(), Sex.FEMALE, cal.getTime()));

        cal.set(1945, Calendar.MARCH, 30);
        musicians.put("ericClapton", musician("Eric Clapton", new ArrayList<Song>(), Sex.MALE, cal.getTime()));

        cal.set(1976, Calendar.DECEMBER, 25);
        musicians.put("arminVanBuuren", musician("Armin van Buuren", new ArrayList<Song>(), Sex.MALE, cal.getTime()));

        cal.set(1986, Calendar.SEPTEMBER, 21);
        musicians.put("lindseyStirling", musician("Lindsey Stirling", new ArrayList<Song>(), Sex.MALE, cal.getTime()));
        
        log.info("Music library musicians loaded.");
    }

    private void createGenres() {
        genres.put("alternativeMusic", genre("Alternative Music"));
        genres.put("blues", genre("Blues"));
        genres.put("classicalMusic", genre("Classical Music"));
        genres.put("country", genre("Country"));
        genres.put("danceMusic", genre("Dance Music"));
        genres.put("electro", genre("Electro"));
        genres.put("hipHop", genre("Hip Hop"));
        genres.put("jazz", genre("Jazz"));
        genres.put("latinMUsic", genre("Latin Music"));
        genres.put("metal", genre("Metal"));
        genres.put("opera", genre("Opera"));
        genres.put("pop", genre("Pop"));
        genres.put("rap", genre("Rap"));
        genres.put("reggae", genre("Reggae"));
        genres.put("rnb", genre("RnB"));
        genres.put("rock", genre("Rock"));
        genres.put("soul", genre("Soul"));
        
        log.info("Music library genres loaded.");
    }

    private void createUsers() {
        user("admin@gmail.com", "admin", "admin", "admin", true);
        user("test@test.com", "Tester99", "test", "test", false);
        user("skywalker@gmail.com", "deathStar1", "Luke", "Skywalker", false);
        user("gandalf@gmail.com", "YouShallNotPass", "Ganfalf", "Grey", false);
        user("thor@gmail.com", "mjolnir123", "Thor", "Odinsson", false);
        
        log.info("Music library users loaded.");
    }

    private Genre genre(String title) {
        Genre g = genreBuilder.title(title).build();

        genreService.create(g);
        return g;
    }

    private Song song(String title, String commentary, int positionInAlbum, double bitrate, Album album, Musician musician, Genre genre) {
        return this.song(title, commentary, positionInAlbum, bitrate, album, musician, genre, null);
    }
    
    private Song song(String title, String commentary, int positionInAlbum, double bitrate, Album album, Musician musician, Genre genre, String youtubeLink) {
        Song s = songBuilder.title(title)
                .commentary(commentary)
                .positionInAlbum(positionInAlbum)
                .bitrate(bitrate)
                .album(album)
                .musician(musician)
                .genre(genre)
                .youtubeLink(youtubeLink)
                .build();

        songService.create(s);
        return s;
    }

    private Album album(String title, String commentary, Date dateOfRelease, byte[] albumArt, String albumArtMimeType, List<Song> songs) {
        Album a = albumBuilder.title(title)
                .commentary(commentary)
                .dateOfRelease(dateOfRelease)
                .albumArt(albumArt)
                .albumArtMimeType(albumArtMimeType)
                .songs(songs)
                .build();

        albumService.create(a);
        return a;
    }

    private Musician musician(String artistName, List<Song> songs, Sex sex, Date dateOfBirth) {
        Musician m = musicianBuilder.artistName(artistName)
                .songs(songs)
                .sex(sex)
                .dateOfBirth(dateOfBirth)
                .build();

        musicianService.create(m);
        return m;
    }

    private User user(String email, String passwordHash, String firstName, String lastName, boolean admin) {
        User u = userBuilder.email(email)
                .passwordHash(passwordHash)
                .firstName(firstName)
                .lastName(lastName)
                .admin(admin)
                .build();

        userService.registerUser(u, u.getPasswordHash());
        return u;
    }

    private byte[] readImage(String file) throws IOException {
        try (InputStream is = this.getClass().getResourceAsStream("/" + file)) {
            int nRead;
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            return buffer.toByteArray();
        }
    }
}
