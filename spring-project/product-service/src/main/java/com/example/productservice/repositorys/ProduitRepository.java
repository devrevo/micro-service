package com.example.productservice.repositorys;


import com.example.productservice.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface ProduitRepository extends JpaRepository<Produit,Long> {
  @RestResource(path = "/byname")
 Page<Produit> findByNameContains(String name, Pageable pageable);
}

