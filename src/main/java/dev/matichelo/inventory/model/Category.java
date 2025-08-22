package dev.matichelo.inventory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /*
    Serializable se usa para permitir que los objetos de la clase se puedan convertir en una secuencia de bytes, útil para almacenamiento en caché, transmisión por red o sesiones. En JPA no es obligatorio, pero se recomienda por compatibilidad con algunos proveedores y herramientas. Si no lo usas, la entidad igual funciona normalmente en la mayoría de los casos.

    El valor 1L es una convención simple y práctica para serialVersionUID. Las diferencias son:
    Mejora la legibilidad del código
    Ayuda a las herramientas de análisis estático
    Documenta que el campo es parte del mecanismo de serialización
    Es opcional pero recomendada
     */

    /*
    Limites de cada tipo de dato:
    - Long: 9,223,372,036,854,775,807  (-2^63 a 2^63-1)
    - String: No tiene un límite fijo, pero en bases de datos suele ser hasta 255 caracteres para VARCHAR o más para TEXT.
    - En JPA, Long es común para IDs, y String para nombres o descripciones.
    - En este caso, Long es adecuado para el ID de la categoría, ya que permite un rango amplio de valores únicos.
    - String es adecuado para el nombre y la descripción, ya que pueden variar en longitud y no tienen un límite estrecho.
    - En general, Long es mejor para IDs y String para textos descriptivos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

}
