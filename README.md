# ensayo-chapter-ct1

http://localhost:8080/swagger-ui/index.html#/


http://localhost:8080/h2-console


A)	Ejercicio

1. Nececesito abrir una cuenta en el Banco en Línea.
   2 Se requiere hacer trnsacciones de débito y credito.
   Que no se pueda hacer trasnferencia a la misma cuenta.
   Que se valide si Tiene Saldo.
   Que valide que la cuenta a la que se va a realizar la transacción este activa.
3. Visuaizar los movimientos de una cuenta.


B)	MODELO

public class Cuenta {

    private Long id;
    
    private Long numeroCuenta;
    
    private BigDecimal monto;
    
              //ACTIVA o INACTIVA
    private String estado;
    
              //Ahorro o corrient
    private String tipoCuenta;

}


public class Cliente {
public Long id;

private String identificacion;

// Cedula o pasaport
private String tipoIdentificacion;

    private String nombres;

    private String apellidos;
}

              La tabla de movimientos Libre modelado


C)	Puntos a Evaluar.

1 Test Unitarios > al menos 3 pruebas
2 Base de datos en Memoria
3 Implementación de Logs
4 Excepciones Personalizadas.
5 Mapeo de Entidades - Dto.
6 Enums.
7 Mapeo.
