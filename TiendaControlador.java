package com.medimarket.controlador;

import com.medimarket.excepciones.AutenticacionException;
import com.medimarket.excepciones.DatosInvalidosException;
import com.medimarket.excepciones.ProductoNoEncontradoException;
import com.medimarket.excepciones.StockInsuficienteException;
import com.medimarket.modelo.*;
import com.medimarket.persistencia.RepositorioPedidos;
import com.medimarket.persistencia.RepositorioProductos;
import com.medimarket.persistencia.RepositorioUsuarios;
import com.medimarket.util.GeneradorId;
import com.medimarket.util.Validador;

import java.util.List;
import java.util.Optional;

/**
 * Controlador central de la lógica de negocio de MediMarket.
 * Coordina las operaciones entre la vista y los repositorios.
 */
public class TiendaControlador {

    private final RepositorioProductos repoProductos;
    private final RepositorioUsuarios  repoUsuarios;
    private final RepositorioPedidos   repoPedidos;
    private final Carrito carrito;
    private Usuario usuarioActual;

    public TiendaControlador() {
        this.repoProductos = new RepositorioProductos("data/productos.dat");
        this.repoUsuarios  = new RepositorioUsuarios("data/usuarios.dat");
        this.repoPedidos   = new RepositorioPedidos("data/pedidos.dat");
        this.carrito       = new Carrito();
    }

    // -------- Autenticación --------
    public Usuario registrar(String nombre, String email, String password,
                             String telefono, String direccion,
                             Usuario.TipoUsuario tipo)
            throws DatosInvalidosException {
        Validador.validarTexto(nombre, "Nombre");
        Validador.validarEmail(email);
        Validador.validarPassword(password);
        Validador.validarTelefono(telefono);
        Validador.validarTexto(direccion, "Dirección");

        if (repoUsuarios.buscarPorEmail(email).isPresent()) {
            throw new DatosInvalidosException(
                    "Ya existe un usuario registrado con ese email.");
        }

        Usuario nuevo = new Usuario(GeneradorId.generar("USR"),
                nombre.trim(), email.trim().toLowerCase(),
                password, telefono.trim(), direccion.trim(), tipo);
        repoUsuarios.guardar(nuevo);
        return nuevo;
    }

    public Usuario iniciarSesion(String email, String password)
            throws AutenticacionException {
        if (email == null || password == null) {
            throw new AutenticacionException("Debe completar todos los campos.");
        }
        Optional<Usuario> userOpt = repoUsuarios.buscarPorEmail(email);
        if (userOpt.isEmpty() || !userOpt.get().getPassword().equals(password)) {
            throw new AutenticacionException("Email o contraseña incorrectos.");
        }
        this.usuarioActual = userOpt.get();
        return this.usuarioActual;
    }

    public void cerrarSesion() {
        this.usuarioActual = null;
        this.carrito.vaciar();
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    // -------- Productos --------
    public List<Producto> listarProductos() {
        return repoProductos.listarTodos();
    }

    public List<Producto> buscarProductos(String texto) {
        return repoProductos.buscarPorNombre(texto);
    }

    public List<Producto> filtrarProductos(Categoria categoria) {
        return repoProductos.filtrarPorCategoria(categoria);
    }

    public List<Producto> productosDestacados() {
        return repoProductos.obtenerDestacados();
    }

    public List<Producto> productosEnOferta() {
        return repoProductos.obtenerEnOferta();
    }

    public Producto obtenerProducto(String id) throws ProductoNoEncontradoException {
        Producto p = repoProductos.buscarPorId(id);
        if (p == null) {
            throw new ProductoNoEncontradoException(
                    "El producto con ID " + id + " no fue encontrado.");
        }
        return p;
    }

    public void guardarProducto(Producto p) {
        repoProductos.guardar(p);
    }

    // -------- Carrito --------
    public Carrito getCarrito() {
        return carrito;
    }

    public void agregarAlCarrito(Producto producto, int cantidad)
            throws StockInsuficienteException {
        carrito.agregarProducto(producto, cantidad);
    }

    public void eliminarDelCarrito(String idProducto) {
        carrito.eliminarProducto(idProducto);
    }

    // -------- Pedidos --------
    public Pedido finalizarCompra(String direccion, String metodoPago)
            throws StockInsuficienteException, DatosInvalidosException {
        if (usuarioActual == null) {
            throw new DatosInvalidosException("Debe iniciar sesión para comprar.");
        }
        if (carrito.isVacio()) {
            throw new DatosInvalidosException("El carrito está vacío.");
        }
        Validador.validarTexto(direccion, "Dirección de entrega");
        Validador.validarTexto(metodoPago, "Método de pago");

        // Reducir stock de cada producto y persistir
        for (ItemCarrito item : carrito.getItems()) {
            Producto p = item.getProducto();
            p.reducirStock(item.getCantidad());
            repoProductos.guardar(p);
        }

        Pedido pedido = new Pedido(
                GeneradorId.generar("PED"),
                usuarioActual.getId(),
                usuarioActual.getNombre(),
                carrito.getItems(),
                carrito.calcularTotal(),
                direccion,
                metodoPago);

        repoPedidos.guardar(pedido);
        carrito.vaciar();
        return pedido;
    }

    public List<Pedido> listarPedidosUsuario() {
        if (usuarioActual == null) return List.of();
        return repoPedidos.listarPorUsuario(usuarioActual.getId());
    }
}
