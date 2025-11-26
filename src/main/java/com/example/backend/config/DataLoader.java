package com.example.backend.config;

import com.example.backend.domain.*;
import com.example.backend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner seedDatabase(EmpleadoRepository empleadoRepository,
                                   ClienteRepository clienteRepository,
                                   PuntoRecoleccionRepository puntoRepository,
                                   RutaRepository rutaRepository,
                                   OrdenServicioRepository ordenServicioRepository) {
        return args -> {
            if (empleadoRepository.count() > 0) {
                return;
            }

            Empleado chofer = new Empleado("Juan Perez", "11.111.111-1", "987654321", "chofer@demo.cl", "demo", UserRole.CHOFER);
            Empleado admin = new Empleado("Ana Admin", "22.222.222-2", "912345678", "admin@demo.cl", "demo", UserRole.ADMIN);
            empleadoRepository.saveAll(List.of(chofer, admin));

            Cliente cliente1 = new Cliente("Eco Plast", "76.111.222-3", "Av. Central 123", "22223333", "contacto@ecoplast.cl",
                    "Plastico", "Semanal", null, chofer.getId(), null);
            Cliente cliente2 = new Cliente("Bio Vidrio", "77.222.333-4", "Camino Verde 456", "22224444", "ventas@biovidrio.cl",
                    "Vidrio", "Mensual", null, chofer.getId(), null);
            clienteRepository.saveAll(List.of(cliente1, cliente2));

            PuntoRecoleccion punto1 = new PuntoRecoleccion(cliente1, "Av. Central 123", "Plastico", "Contenedor azul", "Retiro en bodega");
            PuntoRecoleccion punto2 = new PuntoRecoleccion(cliente2, "Camino Verde 456", "Vidrio", "Jaula", "Precaucion en acceso");
            puntoRepository.saveAll(List.of(punto1, punto2));

            Ruta ruta = new Ruta("Ruta Norte", LocalDate.now(), "PENDIENTE", chofer);
            ruta.setPuntos(List.of(punto1, punto2));
            rutaRepository.save(ruta);

            OrdenServicio orden1 = new OrdenServicio(ruta, punto1, OrdenEstado.PENDIENTE);
            OrdenServicio orden2 = new OrdenServicio(ruta, punto2, OrdenEstado.PENDIENTE);
            ordenServicioRepository.saveAll(List.of(orden1, orden2));
        };
    }
}
