package ma.zyn.app.dao.facade.core.exigence;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.exigence.FamilleExigence;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.exigence.FamilleExigence;
import java.util.List;


@Repository
public interface FamilleExigenceDao extends AbstractRepository<FamilleExigence,Long>  {
    FamilleExigence findByCode(String code);
    int deleteByCode(String code);

    List<FamilleExigence> findByReferentielFamilleExigenceCode(String code);
    List<FamilleExigence> findByReferentielFamilleExigenceId(Long id);
    int deleteByReferentielFamilleExigenceId(Long id);
    int deleteByReferentielFamilleExigenceCode(String code);
    long countByReferentielFamilleExigenceCode(String code);

    @Query("SELECT NEW FamilleExigence(item.id,item.libelle) FROM FamilleExigence item")
    List<FamilleExigence> findAllOptimized();

}
