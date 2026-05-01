-- ==========================================
-- 1. INSERTAR USUARIOS
-- ==========================================
INSERT INTO usuario (nombres, apellidos, correo, fechaCreacion, contraseña, rol, estado) VALUES
('Carlos', 'Sánchez', 'carlosAdmin@gmail.com', '2026-01-02', 'admin123', 'Admin', 'Activo'),
('Jeanpool', 'Rivera', 'jeanpoolAdmin@gmail.com', '2026-02-17', 'admin456', 'Admin', 'Activo'),
('Sebastian', 'Calderon', 'sebastianAdmin@gmail.com', '2026-02-20', 'admin789', 'Admin', 'Activo'),
('Victor', 'Piñas', 'victorAdmin@gmail.com', '2026-03-22','admin012', 'Admin', 'Activo'),
('Blu', 'Valer', 'bluAdmin@gmail.com', '2026-04-25', 'admin345', 'Admin', 'Activo'),
('Carlos', 'Sánchez', 'carlosUser@gmail.com', '2026-01-02', 'user123', 'Usuario', 'Activo'),
('Jeanpool', 'Rivera', 'jeanpoolUser@gmail.com', '2026-02-17', 'user456', 'Usuario', 'Activo'),
('Sebastian', 'Calderon', 'sebastianUser@gmail.com', '2026-02-20', 'user789', 'Usuario', 'Activo'),
('Victor', 'Piñas', 'victorUser@gmail.com', '2026-03-22','user012', 'Usuario', 'Activo'),
('Blu', 'Valer', 'bluUser@gmail.com', '2026-04-25', 'user345', 'Usuario', 'Activo');


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
INSERT INTO producto (codigo, nombre, fecha_creacion ,id_categoria, stock_actual, unidad_medida, stock_minimo, precio_costo, precio_venta, descripcion) VALUES
('PROD-ABA-001', 'Arroz Costeño Extra 5Kg', '2026-04-28', 1, 120, 'Saco',  20, 3.50, 4.80, 'Arroz blanco extra grano largo clasificado'),
('PROD-LAC-001', 'Leche Evaporada Gloria 400g', '2026-04-28', 2, 250,'Lata',  48, 3.20, 4.20, 'Leche evaporada entera clásica etiqueta azul'),
('PROD-BEB-001', 'Gaseosa Inca Kola 3L', '2026-04-28', 3, 60,'Botella',  12, 8.50, 11.50, 'Bebida gaseosa sabor original, envase no retornable'),
('PROD-SNA-001', 'Galletas Casino Menta', '2026-04-28', 4, 100,'Paquete',  15, 0.80, 1.30, 'Galletas dulces rellenas con crema sabor a menta'),
('PROD-LIM-001', 'Detergente Ariel 1kg', '2026-04-28', 6, 45,'Bolsa',  10, 10.00, 14.50, 'Detergente en polvo para ropa blanca y de color'),
('PROD-ABA-002', 'Azúcar Rubia Cartavio 5kg',     '2026-04-28', 1,  80,  'Bolsa',   15, 4.20,  5.80,  'Azúcar rubia de caña, presentación familiar'),
('PROD-LAC-002', 'Yogur Gloria Fresa 1L',          '2026-04-28', 2,  60,  'Botella', 10, 4.50,  6.20,  'Yogur bebible sabor fresa con cultivos activos'),
('PROD-BEB-002', 'Agua San Luis 625ml',            '2026-04-28', 3,  200, 'Botella', 30, 0.70,  1.20,  'Agua mineral sin gas, presentación personal'),
('PROD-SNA-002', 'Chifles Inca´s Food 100g',       '2026-04-28', 4,  150, 'Bolsa',   20, 1.50,  2.50,  'Snack de plátano verde frito con sal'),
('PROD-LIM-002', 'Lavavajillas Ayudín Limón 500g', '2026-04-28', 6,  90,  'Unidad',  12, 3.80,  5.50,  'Crema lavavajillas con fragancia a limón'),
('PROD-ABA-003', 'Fideos Lavaggi Espagueti 500g',  '2026-04-28', 1,  130, 'Paquete', 25, 1.80,  2.80,  'Fideos de trigo semolado, corte espagueti'),
('PROD-LAC-003', 'Queso Fresco Laive 500g',        '2026-04-28', 2,  40,  'Unidad',  8,  7.50,  10.50, 'Queso fresco pasteurizado, textura suave');

-- ==========================================
-- INSERTS TABLA MOVIMIENTO
-- ==========================================

INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Ingreso', '2026-04-28', 'Compra de mercadería semanal', 'Almacén Principal', 'Ingreso con guía de remisión 001-456. Todo conforme.', 1, 1, 390.00);

INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Salida', '2026-04-29', 'Reabastecimiento urgente', 'Almacén Frío', 'Lote con vencimiento a 6 meses.', 6, null, 640.00);

INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Ingreso', '2026-04-10', 'Compra', 'Almacén Principal', 'Factura F001-998', 2, 1, 650.00);

INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Ingreso', '2026-04-12', 'Compra', 'Almacén Frío', 'Ingreso de lácteos por falta de stock', 7, 2, 156.00);

INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Salida', '2026-04-15', 'Despacho', 'Tienda Sede Centro', 'Traslado matutino', 3, null, 40);

INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Salida', '2026-04-18', 'Merma', 'Desechos', 'Cajas abolladas', 8, null, 11.70);

INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Ingreso', '2026-04-25', 'Devolución', 'Almacén Principal', 'La tienda devolvió stock', 4, 1, 90.00);

INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Ingreso', '2026-05-01', 'Compra de insumos de limpieza', 'Almacén Principal', 'Factura F002-1045. Productos recibidos en buen estado.', 9, 3, 215.50);

INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Salida', '2026-05-01', 'Despacho a sucursal', 'Tienda Sede Norte', 'Traslado vespertino autorizado por supervisor.', 5, null, 328.00);

INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Ingreso', '2026-04-30', 'Reposición de stock', 'Almacén Frío', 'Ingreso de lácteos y embutidos. Guía de remisión 003-789.', 10, 2, 174.80);


-- ==========================================
-- INSERTS TABLA DETALLE_MOVIMIENTO
-- ==========================================
-- ==========================================
-- MOVIMIENTO 1
-- ==========================================

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (1, 1, 100, 3.50, 350.00);

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (1, 4, 50, 0.80, 40.00);
-- ==========================================
-- MOVIMIENTO 2
-- ==========================================

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (2, 2, 200, 3.20, 640.00);

-- ==========================================
-- MOVIMIENTO 3
-- ==========================================

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (3, 1, 50, 7.00, 350.00);

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (3, 2, 100, 3.00, 300.00);

-- ==========================================
-- MOVIMIENTO 4
-- ==========================================

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (4, 3, 40, 3.90, 156.00);

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (4, 1, 15, 7.00, 105.00);

-- ==========================================
-- MOVIMIENTO 5
-- ==========================================

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (5, 4, 10, 4.00, 40.00);

-- ==========================================
-- MOVIMIENTO 6
-- ==========================================

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (6, 3, 3, 3.90, 11.70);

-- ==========================================
-- MOVIMIENTO 7
-- ==========================================

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (7, 5, 30, 3.00, 90.00);

-- ==========================================
-- MOVIMIENTO 8
-- ==========================================

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (8, 10, 30, 3.80, 114.00);

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (8, 6,  20, 4.20,  84.00);

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (8, 11, 10, 1.80,  18.00);

-- ==========================================
-- MOVIMIENTO 9
-- ==========================================

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (9, 2,  50, 3.20, 160.00);

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (9, 9,  80, 0.70,  56.00);

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (9, 1,  16, 3.50,  56.00);

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (9, 8,  24, 2.33,  56.00);

-- ==========================================
-- MOVIMIENTO 10
-- ==========================================

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (10, 7,  12, 4.50,  54.00);

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (10, 12,  8, 7.50,  60.00);

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (10, 3,   4, 3.90,  15.60);

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (10, 7,   5, 4.50,  22.50);

INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal)
VALUES (10, 12,  3, 7.50,  22.50);

