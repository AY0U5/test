package ma.zyn.app.bean.core.exigence;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "referentiel_famille_exigence")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="referentiel_famille_exigence_seq",sequenceName="referentiel_famille_exigence_seq",allocationSize=1, initialValue = 1)
public class ReferentielFamilleExigence  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String style;



    public ReferentielFamilleExigence(){
        super();
    }

    public ReferentielFamilleExigence(Long id){
        this.id = id;
    }

    public ReferentielFamilleExigence(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public ReferentielFamilleExigence(String libelle){
        this.libelle = libelle ;
    }
    public ReferentielFamilleExigence(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator = "referentiel_famille_exigence_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReferentielFamilleExigence referentielFamilleExigence = (ReferentielFamilleExigence) o;
        return id != null && id.equals(referentielFamilleExigence.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

