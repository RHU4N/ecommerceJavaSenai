package com.biolab.ecommerce.repositories;

import com.biolab.ecommerce.entities.ItemDoPedido;
import com.biolab.ecommerce.entities.ItemDoPedidoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDoPedidoRepository extends JpaRepository<ItemDoPedido, ItemDoPedidoPK> {

}
