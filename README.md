# MediMarket — Tienda Virtual de Productos Médicos

![Java](https://img.shields.io/badge/Java-21-orange) ![Swing](https://img.shields.io/badge/UI-Swing-blue) ![Status](https://img.shields.io/badge/Status-Funcional-brightgreen)

> *"Tu salud en las mejores manos"*
>
> *"Tecnología médica y bienestar en un solo lugar"*

MediMarket es una aplicación de escritorio en **Java Swing** que simula una
tienda virtual moderna especializada en productos médicos, equipos de salud,
farmacia, dermocosmética, ortopedia, fisioterapia, bioseguridad y más.

Pensada para hospitales, profesionales de la salud y clientes particulares,
con una estética premium en blanco/azul/verde que transmite confianza,
limpieza y profesionalismo.

---

## ✨ Características

### Catálogo
- **34 productos iniciales** distribuidos en **12 categorías**:
  - Equipos médicos · Instrumental quirúrgico · Insumos · Ortopedia
  - Monitoreo de salud · Laboratorio · Farmacia · Dermocosmética
  - Vitaminas · Adulto mayor · Fisioterapia · Bioseguridad
- Productos con marca, stock, descuentos, productos destacados y en oferta.
- Iconografía vectorial médica generada con `Graphics2D` (sin dependencias de
  imágenes externas).

### Funcionalidad de tienda
- 🛒 **Carrito de compras** con cantidades, eliminación y total dinámico.
- 🔍 **Buscador inteligente** por nombre, descripción o marca.
- 🎯 **Filtros** por categoría, ofertas y destacados.
- 📦 **Finalización de compra** con dirección y método de pago, generando
  pedido con reducción automática de stock.
- 👤 **Registro / Login** con validación (email, contraseña, teléfono) y 4
  tipos de usuario (Cliente, Profesional, Institución, Administrador).
- 🧾 **Historial de pedidos** por usuario.

### Secciones de la UI
- **Inicio** — Banner promocional con gradient, ventajas, productos
  destacados, ofertas y grid de categorías.
- **Catálogo** — Listado completo con buscador y filtros.
- **Ofertas** — Productos con descuentos activos.
- **Destacados** — Selección curada.
- **Mis Pedidos** — Historial del usuario logueado.
- **Sobre Nosotros** — Misión, visión y valores.
- **Blog** — 6 entradas de salud y bienestar.
- **Contacto** — Información de contacto y formulario.

### Calidad técnica
- ✅ **POO**: clase abstracta `Producto` con jerarquía polimórfica
  (`EquipoMedico`, `Medicamento`, `InsumoMedico`).
- ✅ **Interfaces**: `Vendible`, `Identificable`, `OperacionesCRUD<T>`
  (genérica).
- ✅ **Excepciones de dominio** verificadas: `StockInsuficienteException`,
  `ProductoNoEncontradoException`, `AutenticacionException`,
  `DatosInvalidosException`.
- ✅ **Persistencia local** vía serialización Java (`.dat` files).
- ✅ **Validaciones** centralizadas con regex (email, password, teléfono,
  texto, números).
- ✅ **Arquitectura por capas**: modelo, persistencia, controlador, vista.

---

## 🚀 Instalación y ejecución

### Requisitos
- **Java 17 o superior** (probado con OpenJDK 21).
- No requiere dependencias externas — solo el JDK estándar.

### Opción 1 — JAR ejecutable (más rápido)

```bash
java -jar MediMarket.jar
```

### Opción 2 — Scripts incluidos

**Linux / macOS:**
```bash
chmod +x run.sh
./run.sh
```

**Windows:**
```bat
run.bat
```

### Opción 3 — Compilar desde código fuente

**Linux / macOS:**
```bash
mkdir -p bin
javac -d bin -sourcepath src $(find src -name "*.java")
java -cp bin com.medimarket.Main
```

**Windows:**
```bat
mkdir bin
dir /s /b src\*.java > sources.txt
javac -d bin -sourcepath src @sources.txt
del sources.txt
java -cp bin com.medimarket.Main
```

---

## 🔐 Credenciales de demostración

La aplicación inicializa dos usuarios de prueba la primera vez que se ejecuta:

| Tipo            | Email                    | Contraseña |
|-----------------|--------------------------|------------|
| Cliente         | `demo@medimarket.com`    | `demo123`  |
| Administrador   | `admin@medimarket.com`   | `admin123` |

También se puede crear un nuevo usuario desde la pestaña **Registrarse** del
diálogo de login.

---

## 📂 Estructura del proyecto

```
MediMarket/
├── src/
│   └── com/medimarket/
│       ├── Main.java                  Punto de entrada
│       ├── modelo/                    Entidades del dominio
│       │   ├── Producto.java          (abstract)
│       │   ├── EquipoMedico.java
│       │   ├── Medicamento.java
│       │   ├── InsumoMedico.java
│       │   ├── Categoria.java         (enum)
│       │   ├── Usuario.java
│       │   ├── Carrito.java
│       │   ├── ItemCarrito.java
│       │   └── Pedido.java
│       ├── interfaces/                Contratos
│       │   ├── Vendible.java
│       │   ├── Identificable.java
│       │   └── OperacionesCRUD.java   (genérica)
│       ├── excepciones/               Excepciones de dominio
│       │   ├── StockInsuficienteException.java
│       │   ├── ProductoNoEncontradoException.java
│       │   ├── AutenticacionException.java
│       │   └── DatosInvalidosException.java
│       ├── persistencia/              Capa de datos
│       │   ├── RepositorioGenerico.java
│       │   ├── RepositorioProductos.java
│       │   ├── RepositorioUsuarios.java
│       │   └── RepositorioPedidos.java
│       ├── controlador/               Lógica de aplicación
│       │   ├── TiendaControlador.java
│       │   └── InicializadorDatos.java
│       ├── util/                      Utilitarios
│       │   ├── Validador.java
│       │   ├── GeneradorId.java
│       │   └── TemaVisual.java
│       └── vista/                     UI Swing
│           ├── VentanaPrincipal.java
│           ├── DialogoLogin.java
│           ├── DialogoCarrito.java
│           ├── DialogoDetalleProducto.java
│           ├── TarjetaProducto.java
│           ├── BotonModerno.java
│           └── IconoMedico.java
├── bin/                               Clases compiladas
├── data/                              Persistencia (creada al primer uso)
│   ├── productos.dat
│   ├── usuarios.dat
│   └── pedidos.dat
├── docs/
│   └── UML.md                         Diagrama UML
├── MediMarket.jar                     JAR ejecutable
├── manifest.txt
├── run.sh                             Linux/macOS
├── run.bat                            Windows
└── README.md                          Este archivo
```

---

## 🎨 Paleta visual

| Color             | Hex        | Uso |
|-------------------|------------|-----|
| Azul primario     | `#00B4D8`  | Botones, énfasis, marca |
| Azul oscuro       | `#023E8A`  | Cabeceras, texto importante |
| Azul claro        | `#CAF0F8`  | Fondos suaves |
| Verde salud       | `#06D6A0`  | Confirmaciones, éxito |
| Blanco / Gris     | `#FFFFFF`, `#F8F9FA` | Fondos generales |
| Rojo              | `#EF476F`  | Ofertas, alertas |
| Amarillo          | `#FFD166`  | Destacados |

---

## 🧱 Diseño UML

Ver el archivo [`docs/UML.md`](docs/UML.md) con el diagrama completo de
clases en notación Mermaid (jerarquía de productos, repositorios genéricos,
interfaces, excepciones y relaciones entre capas).

---

## 🔁 Flujo de uso típico

1. Iniciar aplicación → ventana principal con sección **Inicio**.
2. **Iniciar sesión** con credenciales demo (o registrarse).
3. Navegar al **Catálogo** o filtrar por **Ofertas / Destacados**.
4. Buscar productos en la barra superior.
5. **Ver detalle** de un producto y **agregar al carrito**.
6. Abrir el **carrito** desde el header (badge muestra cantidad).
7. **Finalizar compra**: ingresar dirección y método de pago → genera
   pedido y reduce stock.
8. Consultar **Mis Pedidos** para ver el historial.

---

## 📄 Licencia

Proyecto académico — uso libre para fines educativos.

**MediMarket** — *Innovación para cuidar la vida.*
