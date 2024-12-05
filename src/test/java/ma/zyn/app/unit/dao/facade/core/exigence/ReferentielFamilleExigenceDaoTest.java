package ma.zyn.app.unit.dao.facade.core.exigence;

import ma.zyn.app.bean.core.exigence.ReferentielFamilleExigence;
import ma.zyn.app.dao.facade.core.exigence.ReferentielFamilleExigenceDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ReferentielFamilleExigenceDaoTest {

@Autowired
    private ReferentielFamilleExigenceDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        ReferentielFamilleExigence entity = new ReferentielFamilleExigence();
        entity.setCode(code);
        underTest.save(entity);
        ReferentielFamilleExigence loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        ReferentielFamilleExigence loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        ReferentielFamilleExigence entity = new ReferentielFamilleExigence();
        entity.setId(id);
        underTest.save(entity);
        ReferentielFamilleExigence loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        ReferentielFamilleExigence entity = new ReferentielFamilleExigence();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        ReferentielFamilleExigence loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<ReferentielFamilleExigence> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<ReferentielFamilleExigence> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        ReferentielFamilleExigence given = constructSample(1);
        ReferentielFamilleExigence saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private ReferentielFamilleExigence constructSample(int i) {
		ReferentielFamilleExigence given = new ReferentielFamilleExigence();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setStyle("style-"+i);
        return given;
    }

}
