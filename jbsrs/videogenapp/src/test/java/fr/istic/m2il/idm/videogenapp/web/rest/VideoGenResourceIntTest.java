package fr.istic.m2il.idm.videogenapp.web.rest;

import fr.istic.m2il.idm.videogenapp.VideogenappApp;

import fr.istic.m2il.idm.videogenapp.domain.VideoGen;
import fr.istic.m2il.idm.videogenapp.repository.VideoGenRepository;
import fr.istic.m2il.idm.videogenapp.service.VideoGenService;
import fr.istic.m2il.idm.videogenapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static fr.istic.m2il.idm.videogenapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the VideoGenResource REST controller.
 *
 * @see VideoGenResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = VideogenappApp.class)
public class VideoGenResourceIntTest {

    private static final String DEFAULT_AUTHOR = "AAAAAAAAAA";
    private static final String UPDATED_AUTHOR = "BBBBBBBBBB";

    private static final String DEFAULT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    @Autowired
    private VideoGenRepository videoGenRepository;

    @Autowired
    private VideoGenService videoGenService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVideoGenMockMvc;

    private VideoGen videoGen;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VideoGenResource videoGenResource = new VideoGenResource(videoGenService);
        this.restVideoGenMockMvc = MockMvcBuilders.standaloneSetup(videoGenResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VideoGen createEntity(EntityManager em) {
        VideoGen videoGen = new VideoGen()
            .author(DEFAULT_AUTHOR)
            .date(DEFAULT_DATE)
            .version(DEFAULT_VERSION);
        return videoGen;
    }

    @Before
    public void initTest() {
        videoGen = createEntity(em);
    }

    @Test
    @Transactional
    public void createVideoGen() throws Exception {
        int databaseSizeBeforeCreate = videoGenRepository.findAll().size();

        // Create the VideoGen
        restVideoGenMockMvc.perform(post("/api/video-gens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(videoGen)))
            .andExpect(status().isCreated());

        // Validate the VideoGen in the database
        List<VideoGen> videoGenList = videoGenRepository.findAll();
        assertThat(videoGenList).hasSize(databaseSizeBeforeCreate + 1);
        VideoGen testVideoGen = videoGenList.get(videoGenList.size() - 1);
        assertThat(testVideoGen.getAuthor()).isEqualTo(DEFAULT_AUTHOR);
        assertThat(testVideoGen.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testVideoGen.getVersion()).isEqualTo(DEFAULT_VERSION);
    }

    @Test
    @Transactional
    public void createVideoGenWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = videoGenRepository.findAll().size();

        // Create the VideoGen with an existing ID
        videoGen.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVideoGenMockMvc.perform(post("/api/video-gens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(videoGen)))
            .andExpect(status().isBadRequest());

        // Validate the VideoGen in the database
        List<VideoGen> videoGenList = videoGenRepository.findAll();
        assertThat(videoGenList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllVideoGens() throws Exception {
        // Initialize the database
        videoGenRepository.saveAndFlush(videoGen);

        // Get all the videoGenList
        restVideoGenMockMvc.perform(get("/api/video-gens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(videoGen.getId().intValue())))
            .andExpect(jsonPath("$.[*].author").value(hasItem(DEFAULT_AUTHOR.toString())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION.toString())));
    }

    @Test
    @Transactional
    public void getVideoGen() throws Exception {
        // Initialize the database
        videoGenRepository.saveAndFlush(videoGen);

        // Get the videoGen
        restVideoGenMockMvc.perform(get("/api/video-gens/{id}", videoGen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(videoGen.getId().intValue()))
            .andExpect(jsonPath("$.author").value(DEFAULT_AUTHOR.toString()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingVideoGen() throws Exception {
        // Get the videoGen
        restVideoGenMockMvc.perform(get("/api/video-gens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVideoGen() throws Exception {
        // Initialize the database
        videoGenService.save(videoGen);

        int databaseSizeBeforeUpdate = videoGenRepository.findAll().size();

        // Update the videoGen
        VideoGen updatedVideoGen = videoGenRepository.findOne(videoGen.getId());
        // Disconnect from session so that the updates on updatedVideoGen are not directly saved in db
        em.detach(updatedVideoGen);
        updatedVideoGen
            .author(UPDATED_AUTHOR)
            .date(UPDATED_DATE)
            .version(UPDATED_VERSION);

        restVideoGenMockMvc.perform(put("/api/video-gens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedVideoGen)))
            .andExpect(status().isOk());

        // Validate the VideoGen in the database
        List<VideoGen> videoGenList = videoGenRepository.findAll();
        assertThat(videoGenList).hasSize(databaseSizeBeforeUpdate);
        VideoGen testVideoGen = videoGenList.get(videoGenList.size() - 1);
        assertThat(testVideoGen.getAuthor()).isEqualTo(UPDATED_AUTHOR);
        assertThat(testVideoGen.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testVideoGen.getVersion()).isEqualTo(UPDATED_VERSION);
    }

    @Test
    @Transactional
    public void updateNonExistingVideoGen() throws Exception {
        int databaseSizeBeforeUpdate = videoGenRepository.findAll().size();

        // Create the VideoGen

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restVideoGenMockMvc.perform(put("/api/video-gens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(videoGen)))
            .andExpect(status().isCreated());

        // Validate the VideoGen in the database
        List<VideoGen> videoGenList = videoGenRepository.findAll();
        assertThat(videoGenList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteVideoGen() throws Exception {
        // Initialize the database
        videoGenService.save(videoGen);

        int databaseSizeBeforeDelete = videoGenRepository.findAll().size();

        // Get the videoGen
        restVideoGenMockMvc.perform(delete("/api/video-gens/{id}", videoGen.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<VideoGen> videoGenList = videoGenRepository.findAll();
        assertThat(videoGenList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VideoGen.class);
        VideoGen videoGen1 = new VideoGen();
        videoGen1.setId(1L);
        VideoGen videoGen2 = new VideoGen();
        videoGen2.setId(videoGen1.getId());
        assertThat(videoGen1).isEqualTo(videoGen2);
        videoGen2.setId(2L);
        assertThat(videoGen1).isNotEqualTo(videoGen2);
        videoGen1.setId(null);
        assertThat(videoGen1).isNotEqualTo(videoGen2);
    }
}
