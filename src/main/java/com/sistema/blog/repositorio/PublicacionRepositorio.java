package com.sistema.blog.repositorio;

import com.sistema.blog.entidades.Publicaciones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacionRepositorio extends JpaRepository<Publicaciones, Long> {
}
