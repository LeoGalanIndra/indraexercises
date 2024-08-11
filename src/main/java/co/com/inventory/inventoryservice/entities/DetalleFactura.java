package co.com.inventory.inventoryservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "detalle_factura")
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_factura", nullable = false)
    private Factura factura;

    @Column(name = "id_producto", nullable = false)
    private Long idProducto;

    @Column(name = "precio_producto", nullable = false)
    private BigDecimal precioProducto;

    @Column(name = "cantidad_producto", nullable = false)
    private Integer cantidadProducto;

    @Column(name = "impuesto_producto", nullable = false)
    private BigDecimal impuestoProducto;

    @Column(name = "total_producto", nullable = false)
    private BigDecimal totalProducto;


}

