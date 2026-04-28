-- ==========================================
-- 1. INSERTAR USUARIOS
-- ==========================================
INSERT INTO usuario (nombres, apellidos, correo, fechaCreacion, contraseña, rol, estado) VALUES
('Carlos Alonso', 'Sánchez Ramos', 'carlos.sanchez@almxn.com', '2026-04-28', 'admin123', 'Admin', 'Activo'),
('Jeanpool', 'Flores', 'jeanpool@almxn.com', '2026-03-23', 'user123', 'Usuario', 'Activo'),
('Sebastian', 'Vidal', 'sebastian@almxn.com', '2026-04-28', 'user123', 'Usuario', 'Suspendido'),
('Victor', 'Mendoza', 'victor@almxn.com', '2026-04-28','user123', 'Usuario', 'Bloqueado');


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
-- 4. INSERTAR PRODUCTOS (Alineados a tu CRUD)
-- ==========================================
-- Nota: id_categoria 1=Almacenamiento, 2=Periféricos, 3=Componentes, 4=Monitores
INSERT INTO producto (codigo, nombre, fecha_creacion ,id_categoria, stock_actual, unidad_medida, stock_minimo, precio_costo, precio_venta, descripcion) VALUES
('PROD-ABA-001', 'Arroz Costeño Extra 5Kg', '2026-04-28', 1, 120, 'Saco',  20, 3.50, 4.80, 'Arroz blanco extra grano largo clasificado'),
('PROD-LAC-001', 'Leche Evaporada Gloria 400g', '2026-04-28', 2, 250,'Lata',  48, 3.20, 4.20, 'Leche evaporada entera clásica etiqueta azul'),
('PROD-BEB-001', 'Gaseosa Inca Kola 3L', '2026-04-28', 3, 60,'Botella',  12, 8.50, 11.50, 'Bebida gaseosa sabor original, envase no retornable'),
('PROD-SNA-001', 'Galletas Casino Menta', '2026-04-28', 4, 100,'Paquete',  15, 0.80, 1.30, 'Galletas dulces rellenas con crema sabor a menta'),
('PROD-LIM-001', 'Detergente Ariel 1kg', '2026-04-28', 6, 45,'Bolsa',  10, 10.00, 14.50, 'Detergente en polvo para ropa blanca y de color');