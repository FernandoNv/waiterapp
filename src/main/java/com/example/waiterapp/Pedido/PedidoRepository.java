package com.example.waiterapp.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("" +
            "SELECT DISTINCT pedido FROM Pedido pedido INNER JOIN pedido.cliente c WHERE pedido.cliente.id = c.id AND c.id = :idCliente")
    public List<Pedido> findallByIdCliente(@Param("idCliente") Long idCliente);
}
