package  ma.zyn.app.ws.facade.admin.projet;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.projet.DossierProjet;
import ma.zyn.app.dao.criteria.core.projet.DossierProjetCriteria;
import ma.zyn.app.service.facade.admin.projet.DossierProjetAdminService;
import ma.zyn.app.ws.converter.projet.DossierProjetConverter;
import ma.zyn.app.ws.dto.projet.DossierProjetDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/dossierProjet/")
public class DossierProjetRestAdmin {




    @Operation(summary = "Finds a list of all dossierProjets")
    @GetMapping("")
    public ResponseEntity<List<DossierProjetDto>> findAll() throws Exception {
        ResponseEntity<List<DossierProjetDto>> res = null;
        List<DossierProjet> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        List<DossierProjetDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all dossierProjets")
    @GetMapping("optimized")
    public ResponseEntity<List<DossierProjetDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<DossierProjetDto>> res = null;
        List<DossierProjet> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        List<DossierProjetDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a dossierProjet by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DossierProjetDto> findById(@PathVariable Long id) {
        DossierProjet t = service.findById(id);
        if (t != null) {
            converter.init(true);
            DossierProjetDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a dossierProjet by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<DossierProjetDto> findByLibelle(@PathVariable String libelle) {
	    DossierProjet t = service.findByReferenceEntity(new DossierProjet(libelle));
        if (t != null) {
            converter.init(true);
            DossierProjetDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  dossierProjet")
    @PostMapping("")
    public ResponseEntity<DossierProjetDto> save(@RequestBody DossierProjetDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            DossierProjet myT = converter.toItem(dto);
            DossierProjet t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DossierProjetDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  dossierProjet")
    @PutMapping("")
    public ResponseEntity<DossierProjetDto> update(@RequestBody DossierProjetDto dto) throws Exception {
        ResponseEntity<DossierProjetDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DossierProjet t = service.findById(dto.getId());
            converter.copy(dto,t);
            DossierProjet updated = service.update(t);
            DossierProjetDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of dossierProjet")
    @PostMapping("multiple")
    public ResponseEntity<List<DossierProjetDto>> delete(@RequestBody List<DossierProjetDto> dtos) throws Exception {
        ResponseEntity<List<DossierProjetDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<DossierProjet> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified dossierProjet")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }


    @Operation(summary = "Finds a dossierProjet and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DossierProjetDto> findWithAssociatedLists(@PathVariable Long id) {
        DossierProjet loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        DossierProjetDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds dossierProjets by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DossierProjetDto>> findByCriteria(@RequestBody DossierProjetCriteria criteria) throws Exception {
        ResponseEntity<List<DossierProjetDto>> res = null;
        List<DossierProjet> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        List<DossierProjetDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated dossierProjets by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DossierProjetCriteria criteria) throws Exception {
        List<DossierProjet> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        List<DossierProjetDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets dossierProjet data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DossierProjetCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DossierProjetDto> findDtos(List<DossierProjet> list){
        converter.initList(false);
        List<DossierProjetDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DossierProjetDto> getDtoResponseEntity(DossierProjetDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public DossierProjetRestAdmin(DossierProjetAdminService service, DossierProjetConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final DossierProjetAdminService service;
    private final DossierProjetConverter converter;





}
