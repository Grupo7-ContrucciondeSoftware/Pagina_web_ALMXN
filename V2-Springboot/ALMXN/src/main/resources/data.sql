-- ==========================================
-- 1. INSERTAR USUARIOS
-- ==========================================
INSERT INTO usuario (nombres, apellidos, correo, fechaCreacion, contraseña, rol, estado) VALUES
('Carlos', 'Sánchez', 'carlosAdmin@gmail.com', '2026-04-28', 'admin123', 'Admin', 'Activo'),
('Jeanpool', 'Flores', 'jeanpoolAdmin@gmail.com', '2026-03-23', 'admin456', 'Admin', 'Activo'),
('Sebastian', 'Vidal', 'sebastianAdmin@gmail.com', '2026-04-28', 'admin789', 'Admin', 'Activo'),
('Victor', 'Mendoza', 'victorAdmin@gmail.com', '2026-04-28','admin012', 'Admin', 'Activo'),
('Carlos', 'Sánchez', 'carlosUser@gmail.com', '2026-04-28', 'user123', 'Usuario', 'Activo'),
('Jeanpool', 'Flores', 'jeanpoolUser@gmail.com', '2026-03-23', 'user456', 'Usuario', 'Activo'),
('Sebastian', 'Vidal', 'sebastianUser@gmail.com', '2026-04-28', 'user789', 'Usuario', 'Activo'),
('Victor', 'Mendoza', 'victorUser@gmail.com', '2026-04-28','user012', 'Usuario', 'Activo');


-- ==========================================
-- 2. INSERTAR CATEGORÍAS
-- ==========================================
INSERT INTO categoria (nombre, descripcion) VALUES
('Abarrotes', 'Productos básicos de despensa y uso diario'),
('Lácteos y Huevos', 'Leche, quesos, mantequillas, yogures y derivados avícolas'),
('Bebidas y Licores', 'Aguas, gaseosas, jugos y bebidas alcohólicas'),
('Snacks y Confitería', 'Piqueos, galletas, chocolates y dulces'),
('Frutas y Verduras', 'Productos agrícolas frescos'),
('Limpieza y Hogar', 'Detergentes, desinfectantes y artículos de aseo'),
('Cuidado Personal', 'Higiene, cosmética y cuidado corporal'),
('Embutidos y Fríos', 'Carnes procesadas, jamones y salchichas'),
('Panadería y Pastelería', 'Panes, tortas y postres horneados');


-- ==========================================
-- 3. INSERTAR PROVEEDORES
-- ==========================================
INSERT INTO proveedor (ruc, razon_social, telefono, correo) VALUES
('20100055237', 'Alicorp S.A.A.', '987654321', 'ventas@alicorp.com.pe'),
('20262992453', 'Gloria S.A.', '912345678', 'distribucion@gloria.com.pe'),
('20100125896', 'Procter & Gamble Perú', '999888777', 'pedidos@pg.com.pe');


-- ==========================================
-- 4. INSERTAR PRODUCTOS
-- ==========================================
-- Nota: id_categoria 1=Almacenamiento, 2=Periféricos, 3=Componentes, 4=Monitores
INSERT INTO producto (codigo, nombre, fecha_creacion ,id_categoria, stock_actual, unidad_medida, stock_minimo, precio_costo, precio_venta, descripcion) VALUES
('PROD-ABA-001', 'Arroz Costeño Extra 5Kg', '2026-04-28', 1, 120, 'Saco',  20, 3.50, 4.80, 'Arroz blanco extra grano largo clasificado'),
('PROD-LAC-001', 'Leche Evaporada Gloria 400g', '2026-04-28', 2, 250,'Lata',  48, 3.20, 4.20, 'Leche evaporada entera clásica etiqueta azul'),
('PROD-BEB-001', 'Gaseosa Inca Kola 3L', '2026-04-28', 3, 60,'Botella',  12, 8.50, 11.50, 'Bebida gaseosa sabor original, envase no retornable'),
('PROD-SNA-001', 'Galletas Casino Menta', '2026-04-28', 4, 100,'Paquete',  15, 0.80, 1.30, 'Galletas dulces rellenas con crema sabor a menta'),
('PROD-LIM-001', 'Detergente Ariel 1kg', '2026-04-28', 6, 45,'Bolsa',  10, 10.00, 14.50, 'Detergente en polvo para ropa blanca y de color');

-- ==========================================
-- INSERTS TABLA MOVIMIENTO
-- ==========================================

INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Ingreso', '2026-04-28', 'Compra de mercadería semanal', 'Almacén Principal', 'Ingreso con guía de remisión 001-456. Todo conforme.', 1, 1, 390.00);

INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Salida', '2026-04-29', 'Reabastecimiento urgente', 'Almacén Frío', 'Lote con vencimiento a 6 meses.', 2, null, 640.00);

INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Ingreso', '2026-04-10', 'Compra', 'Almacén Principal', 'Factura F001-998', 1, 1, 650.00);

INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Ingreso', '2026-04-12', 'Compra', 'Almacén Frío', 'Ingreso de lácteos por falta de stock', 2, 2, 156.00);

INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Salida', '2026-04-15', 'Despacho', 'Tienda Sede Centro', 'Traslado matutino', 1, null, 40);

INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Salida', '2026-04-18', 'Merma', 'Desechos', 'Cajas abolladas', 2, null, 11.70);

INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Ingreso', '2026-04-25', 'Devolución', 'Almacén Principal', 'La tienda devolvió stock', 1, 1, 90.00);

-- ==========================================
-- INSERTS TABLA DETALLE_MOVIMIENTO
-- ==========================================

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (1, 1, 100, 3.50, 350.00);

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (1, 4, 50, 0.80, 40.00);

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (2, 2, 200, 3.20, 640.00);

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (3, 1, 50, 7.00, 350.00);

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (3, 2, 100, 3.00, 300.00);

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (4, 3, 40, 3.90, 156.00);

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (4, 1, 15, 7.00, 105.00);

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (5, 4, 10, 4.00, 40.00);

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (6, 3, 3, 3.90, 11.70);

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (7, 5, 30, 3.00, 90.00);


