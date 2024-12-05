package ma.zyn.app.unit.dao.facade.core.exigence;

import ma.zyn.app.bean.core.exigence.FamilleExigence;
import ma.zyn.app.dao.facade.core.exigence.FamilleExigenceDao;

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

import ma.zyn.app.bean.core.exigence.ReferentielFamilleExigence ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class FamilleExigenceDaoTest {

@Autowired
    private FamilleExigenceDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        FamilleExigence entity = new FamilleExigence();
        entity.setCode(code);
        underTest.save(entity);
        FamilleExigence loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        FamilleExigence loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        FamilleExigence entity = new FamilleExigence();
        entity.setId(id);
        underTest.save(entity);
        FamilleExigence loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        FamilleExigence entity = new FamilleExigence();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        FamilleExigence loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<FamilleExigence> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<FamilleExigence> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        FamilleExigence given = constructSample(1);
        FamilleExigence saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private FamilleExigence constructSample(int i) {
		FamilleExigence given = new FamilleExigence();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setStyle("style-"+i);
        given.setReferentielFamilleExigence(new ReferentielFamilleExigence(1L));
        return given;
    }

}
