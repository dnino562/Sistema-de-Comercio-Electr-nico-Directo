package com.medimarket.controlador;

import com.medimarket.modelo.*;
import com.medimarket.persistencia.RepositorioProductos;
import com.medimarket.persistencia.RepositorioUsuarios;

import java.time.LocalDate;
import java.util.List;

/**
 * Inicializa el catálogo de productos y un usuario administrador
 * la primera vez que se ejecuta la aplicación.
 */
public class InicializadorDatos {

    public static void inicializarSiVacio() {
        RepositorioProductos repoProd = new RepositorioProductos("data/productos.dat");
        RepositorioUsuarios  repoUsr  = new RepositorioUsuarios("data/usuarios.dat");

        if (repoProd.listarTodos().isEmpty()) {
            cargarCatalogo(repoProd);
        }
        if (repoUsr.listarTodos().isEmpty()) {
            // Usuario demo predefinido
            Usuario demo = new Usuario("USR-DEMO0001", "Cliente Demo",
                    "demo@medimarket.com", "demo123",
                    "+57 300 000 0000", "Calle 123 #45-67",
                    Usuario.TipoUsuario.CLIENTE);
            repoUsr.guardar(demo);

            Usuario admin = new Usuario("USR-ADMIN001", "Administrador",
                    "admin@medimarket.com", "admin123",
                    "+57 301 000 0000", "Sede MediMarket",
                    Usuario.TipoUsuario.ADMINISTRADOR);
            repoUsr.guardar(admin);
        }
    }

    private static void cargarCatalogo(RepositorioProductos repo) {
        List<Producto> productos = List.of(
            // EQUIPOS MÉDICOS
            crearEquipo("EQ-001", "Tensiómetro Digital Premium",
                    "Tensiómetro digital de brazo, pantalla LCD, memoria para 60 lecturas.",
                    189900, 25, "OmronCare", "110V", 24, true, 0.10),
            crearEquipo("EQ-002", "Oxímetro de Pulso",
                    "Mide saturación de oxígeno y frecuencia cardíaca con alta precisión.",
                    89900, 50, "PulseTech", "Pilas AAA", 12, true, 0.15),
            crearEquipo("EQ-003", "Nebulizador Ultrasónico",
                    "Nebulizador silencioso ideal para niños y adultos.",
                    229900, 18, "MedAir", "110V", 18, false, 0.0),
            crearEquipo("EQ-004", "Termómetro Infrarrojo",
                    "Lectura sin contacto en 1 segundo, certificado clínico.",
                    119900, 60, "TempScan", "Pilas AA", 12, true, 0.20),
            crearEquipo("EQ-005", "Glucómetro Digital",
                    "Monitor de glucosa con 50 tiras reactivas incluidas.",
                    149900, 40, "GlucoCheck", "Pila litio", 24, false, 0.05),

            // INSTRUMENTAL QUIRÚRGICO
            crearInsumo("IQ-001", "Set de Cirugía Menor",
                    "Set quirúrgico de 15 piezas en acero inoxidable esterilizable.",
                    459000, 12, "SurgicalPro", Categoria.INSTRUMENTAL_QUIRURGICO,
                    "Acero inoxidable", true, "Set", false, 0.0),
            crearInsumo("IQ-002", "Pinza Hemostática",
                    "Pinza Kelly curva 14cm, acero quirúrgico.",
                    45000, 80, "MediTools", Categoria.INSTRUMENTAL_QUIRURGICO,
                    "Acero quirúrgico", true, "Unidad", false, 0.0),

            // INSUMOS MÉDICOS
            crearInsumo("IN-001", "Gasas Estériles 10x10 cm (paquete x100)",
                    "Gasas tejidas estériles para curaciones, paquete por 100 unidades.",
                    35900, 200, "PuriMed", Categoria.INSUMOS_MEDICOS,
                    "Algodón", true, "Paquete", true, 0.10),
            crearInsumo("IN-002", "Jeringas Desechables 5ml (caja x100)",
                    "Jeringas estériles desechables, aguja calibre 21G.",
                    49900, 150, "MediSafe", Categoria.INSUMOS_MEDICOS,
                    "Polipropileno", true, "Caja", false, 0.0),
            crearInsumo("IN-003", "Vendas Elásticas (caja x12)",
                    "Vendas elásticas de 5cm para inmovilización y soporte.",
                    29900, 100, "FlexMed", Categoria.INSUMOS_MEDICOS,
                    "Algodón elastizado", false, "Caja", false, 0.0),

            // ORTOPEDIA
            crearInsumo("OR-001", "Rodillera Ortopédica Profesional",
                    "Rodillera con soporte lateral, talla regulable.",
                    79900, 35, "OrthoFit", Categoria.ORTOPEDIA,
                    "Neopreno", false, "Unidad", true, 0.15),
            crearInsumo("OR-002", "Muletas de Aluminio Regulables",
                    "Par de muletas axilares, ajustables en altura.",
                    119000, 20, "MobilityPro", Categoria.ORTOPEDIA,
                    "Aluminio", false, "Par", false, 0.0),
            crearInsumo("OR-003", "Collarín Cervical Blando",
                    "Collarín ergonómico para soporte cervical.",
                    34900, 45, "CervicCare", Categoria.ORTOPEDIA,
                    "Espuma médica", false, "Unidad", false, 0.0),

            // MONITOREO DE SALUD
            crearEquipo("MS-001", "Báscula Digital con Bioimpedancia",
                    "Báscula inteligente con análisis corporal completo y app.",
                    219900, 30, "BodyTech", "Pilas AAA", 24, true, 0.20),
            crearEquipo("MS-002", "Smartwatch Salud Pro",
                    "Reloj con monitoreo cardíaco, SpO2, sueño y ECG.",
                    389000, 22, "VitaWatch", "Recargable", 12, true, 0.10),

            // LABORATORIO
            crearInsumo("LB-001", "Microscopio Profesional 1000X",
                    "Microscopio binocular para laboratorio clínico.",
                    1290000, 6, "OpticLab", Categoria.LABORATORIO,
                    "Metal y vidrio óptico", false, "Unidad", true, 0.0),
            crearInsumo("LB-002", "Centrífuga de Laboratorio 8 tubos",
                    "Centrífuga digital, hasta 4000 RPM.",
                    1590000, 4, "LabSpin", Categoria.LABORATORIO,
                    "Metal", false, "Unidad", false, 0.05),

            // FARMACIA
            crearMedicamento("FA-001", "Acetaminofén 500mg (caja x20)",
                    "Analgésico y antipirético de uso general.",
                    8900, 200, "FarmaCol", "Acetaminofén",
                    "Tabletas 500mg", LocalDate.of(2027, 6, 30), false, false, 0.0),
            crearMedicamento("FA-002", "Ibuprofeno 400mg (caja x30)",
                    "Antiinflamatorio no esteroideo.",
                    14500, 180, "PharmaPlus", "Ibuprofeno",
                    "Tabletas 400mg", LocalDate.of(2027, 4, 15), false, false, 0.0),

            // DERMOCOSMÉTICA
            crearInsumo("DC-001", "Protector Solar FPS 50+ Facial",
                    "Protector solar dermatológico, no comedogénico, 50ml.",
                    69900, 80, "DermaShield", Categoria.DERMOCOSMETICA,
                    "Crema dermatológica", false, "Frasco 50ml", true, 0.15),
            crearInsumo("DC-002", "Crema Reparadora con Ácido Hialurónico",
                    "Crema antiedad con ácido hialurónico y vitamina E.",
                    119900, 50, "BeautyMed", Categoria.DERMOCOSMETICA,
                    "Crema", false, "Frasco 30ml", false, 0.10),
            crearInsumo("DC-003", "Limpiador Facial Dermatológico",
                    "Limpiador suave para pieles sensibles.",
                    49900, 70, "PureSkin", Categoria.DERMOCOSMETICA,
                    "Gel", false, "Frasco 200ml", false, 0.0),

            // VITAMINAS
            crearMedicamento("VT-001", "Multivitamínico Adultos (60 tabletas)",
                    "Suplemento completo con 13 vitaminas y 9 minerales.",
                    69900, 90, "VitaMax", "Multivitamínico",
                    "Tabletas", LocalDate.of(2027, 12, 31), false, true, 0.10),
            crearMedicamento("VT-002", "Vitamina C 1000mg (90 cápsulas)",
                    "Refuerzo del sistema inmunológico.",
                    49900, 100, "ImmunPlus", "Ácido ascórbico",
                    "Cápsulas 1000mg", LocalDate.of(2027, 8, 30), false, false, 0.0),
            crearMedicamento("VT-003", "Omega 3 Premium (120 cápsulas)",
                    "Aceite de pescado purificado, alta concentración EPA/DHA.",
                    89900, 75, "MarineLife", "Omega 3",
                    "Cápsulas blandas", LocalDate.of(2028, 1, 15), false, true, 0.20),

            // ADULTO MAYOR
            crearInsumo("AM-001", "Andador Plegable de Aluminio",
                    "Andador con ruedas y asiento, plegable.",
                    249000, 15, "SeniorCare", Categoria.ADULTO_MAYOR,
                    "Aluminio reforzado", false, "Unidad", true, 0.10),
            crearInsumo("AM-002", "Pañales Adulto Talla M (paquete x30)",
                    "Pañales con alta absorción y ajuste anatómico.",
                    79900, 60, "DryComfort", Categoria.ADULTO_MAYOR,
                    "Algodón absorbente", false, "Paquete", false, 0.05),

            // FISIOTERAPIA
            crearInsumo("FT-001", "Pelota de Pilates 65cm",
                    "Balón antiestallido para fisioterapia y ejercicio.",
                    59900, 40, "FitTherapy", Categoria.FISIOTERAPIA,
                    "PVC reforzado", false, "Unidad", false, 0.0),
            crearInsumo("FT-002", "Set de Bandas Elásticas (5 niveles)",
                    "Bandas elásticas de resistencia para rehabilitación.",
                    49900, 70, "FlexBand", Categoria.FISIOTERAPIA,
                    "Látex", false, "Set", true, 0.15),
            crearInsumo("FT-003", "Compresa Térmica Reutilizable",
                    "Compresa de gel para frío o calor terapéutico.",
                    24900, 90, "ThermoMed", Categoria.FISIOTERAPIA,
                    "Gel y vinilo", false, "Unidad", false, 0.0),

            // BIOSEGURIDAD
            crearInsumo("BS-001", "Mascarillas Quirúrgicas (caja x50)",
                    "Mascarillas tricapa con elástico, certificación médica.",
                    19900, 300, "SafeMed", Categoria.BIOSEGURIDAD,
                    "Polipropileno", false, "Caja", true, 0.20),
            crearInsumo("BS-002", "Guantes de Nitrilo Talla M (caja x100)",
                    "Guantes desechables sin polvo, alta resistencia.",
                    39900, 150, "ProtecMed", Categoria.BIOSEGURIDAD,
                    "Nitrilo", false, "Caja", true, 0.10),
            crearInsumo("BS-003", "Gel Antibacterial 1L",
                    "Gel desinfectante con 70% de alcohol.",
                    29900, 200, "CleanHands", Categoria.BIOSEGURIDAD,
                    "Solución alcohólica", false, "Botella 1L", false, 0.05),
            crearInsumo("BS-004", "Alcohol Antiséptico 70% (1 litro)",
                    "Alcohol etílico para uso médico y desinfección.",
                    14900, 250, "PuriClean", Categoria.BIOSEGURIDAD,
                    "Alcohol etílico", false, "Botella 1L", false, 0.0)
        );

        for (Producto p : productos) {
            repo.guardar(p);
        }
    }

    private static EquipoMedico crearEquipo(String id, String nombre, String desc,
                                            double precio, int stock, String marca,
                                            String voltaje, int garantia,
                                            boolean destacado, double descuento) {
        EquipoMedico e = new EquipoMedico(id, nombre, desc, precio, stock, marca, voltaje, garantia);
        e.setDestacado(destacado);
        e.setDescuento(descuento);
        return e;
    }

    private static InsumoMedico crearInsumo(String id, String nombre, String desc,
                                            double precio, int stock, String marca,
                                            Categoria cat, String material,
                                            boolean esteril, String unidad,
                                            boolean destacado, double descuento) {
        InsumoMedico i = new InsumoMedico(id, nombre, desc, precio, stock, marca, cat, material, esteril, unidad);
        i.setDestacado(destacado);
        i.setDescuento(descuento);
        return i;
    }

    private static Medicamento crearMedicamento(String id, String nombre, String desc,
                                                double precio, int stock, String marca,
                                                String principio, String presentacion,
                                                LocalDate vence, boolean receta,
                                                boolean destacado, double descuento) {
        Categoria cat = nombre.toLowerCase().contains("vitamina") ||
                        nombre.toLowerCase().contains("omega") ||
                        nombre.toLowerCase().contains("multivitam")
                ? Categoria.VITAMINAS : Categoria.FARMACIA;
        Medicamento m = new Medicamento(id, nombre, desc, precio, stock, marca, cat,
                principio, presentacion, vence, receta);
        m.setDestacado(destacado);
        m.setDescuento(descuento);
        return m;
    }
}
