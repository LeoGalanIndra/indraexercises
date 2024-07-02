package co.com.inventory.inventoryservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "factura",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "nro_recibo")
        })
public class Factura {

    @Id
    private String id;

    @Column(name = "nro_recibo", nullable = false, unique = true)
    private String nroRecibo;

    @Column(name = "fecha_recibo", nullable = false)
    private LocalDate fechaRecibo;

    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;

    @Column(name = "nombre_cliente", nullable = false)
    private String nombreCliente;

    @Column(name = "email_cliente", nullable = false)
    private String emailCliente;

    @Column(name = "requiere_factura", nullable = false)
    private Boolean requiereFactura;

    @Column(name = "sub_total", nullable = false)
    private BigDecimal subTotal;

    @Column(name = "total_impuestos", nullable = false)
    private BigDecimal totalImpuestos;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Column(name = "total_pagado", nullable = false)
    private BigDecimal totalPagado;

    @Column(name = "cambio", nullable = false)
    private BigDecimal cambio;

    @OneToMany(mappedBy = "factura")
    private List<DetalleFactura> items ;


}
