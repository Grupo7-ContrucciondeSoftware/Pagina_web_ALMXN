-- ==========================================
-- 1. TABLA CATEGORÍA
-- ==========================================

CREATE TABLE categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    estado VARCHAR(20) DEFAULT 'Activo'
);

-- ==========================================
-- 2. TABLA PROVEEDOR
-- ==========================================

CREATE TABLE proveedor (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    ruc VARCHAR(11) UNIQUE NOT NULL,
    razon_social VARCHAR(150) NOT NULL,
    telefono VARCHAR(15),
    correo VARCHAR(100)
);

-- ==========================================
-- 3. TABLA USUARIO
-- ==========================================

CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    fechaCreacion DATE DEFAULT CURRENT_DATE,
    contraseña VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL,
    estado VARCHAR(20) DEFAULT 'Activo'
);

-- ==========================================
-- 4. TABLA PRODUCTO
-- ==========================================

CREATE TABLE producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(50) UNIQUE NOT NULL,
    fecha_creacion DATE DEFAULT CURRENT_DATE,
    nombre VARCHAR(150) NOT NULL,
    id_categoria INT NOT NULL,
    stock_actual INT NOT NULL,
    unidad_medida VARCHAR(20) NOT NULL,
    precio_costo DECIMAL(10,2) NOT NULL,
    precio_venta DECIMAL(10,2) NOT NULL,
    descripcion VARCHAR(255),
    estado VARCHAR(20) DEFAULT 'Activo',
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

-- ==========================================
-- 5. TABLA DE MOVIMIENTOS
-- ==========================================

CREATE TABLE movimiento (
    id_movimiento INT AUTO_INCREMENT PRIMARY KEY,
    tipo_movimiento VARCHAR(20) NOT NULL,
    fecha_movimiento DATE NOT NULL,
    motivo VARCHAR(100),
    destino VARCHAR(150),
    observaciones TEXT,
    id_usuario INT NOT NULL,
    id_proveedor INT,
    total_movimiento DECIMAL(10,2) DEFAULT 0.00,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
);

-- ==========================================
-- 6. TABLA DETALLE MOVIMIENTO
-- ==========================================

CREATE TABLE detalle_movimiento (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_movimiento INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_movimiento) REFERENCES movimiento(id_movimiento),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);