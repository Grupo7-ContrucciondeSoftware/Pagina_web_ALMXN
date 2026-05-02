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
('Abarrotes',           'Productos básicos de despensa y uso diario'),
('Lácteos y Huevos',    'Leche, quesos, mantequillas, yogures y derivados avícolas'),
('Bebidas y Licores',   'Aguas, gaseosas, jugos y bebidas alcohólicas'),
('Snacks y Confitería', 'Piqueos, galletas, chocolates y dulces'),
('Frutas y Verduras',   'Productos agrícolas frescos'),
('Limpieza y Hogar',    'Detergentes, desinfectantes y artículos de aseo'),
('Cuidado Personal',    'Higiene, cosmética y cuidado corporal'),
('Embutidos y Fríos',   'Carnes procesadas, jamones y salchichas'),
('Panadería y Pastelería', 'Panes, tortas y postres horneados'),
('Conservas y Enlatados',  'Atunes, sardinas, menestras enlatadas y conservas vegetales'),
('Aceites y Condimentos',  'Aceites vegetales, vinagres, salsas y especias'),
('Mascotas',               'Alimentos y accesorios para animales domésticos');


-- ==========================================
-- 3. INSERTAR PROVEEDORES
-- ==========================================
INSERT INTO proveedor (ruc, razon_social, telefono, correo) VALUES
('20100055237', 'Alicorp S.A.A.',             '987654321', 'ventas@alicorp.com.pe'),
('20262992453', 'Gloria S.A.',                '912345678', 'distribucion@gloria.com.pe'),
('20100125896', 'Procter & Gamble Perú',       '999888777', 'pedidos@pg.com.pe'),
('20331066703', 'Backus & Johnston S.A.A.',    '981234567', 'comercial@backus.pe'),
('20418108289', 'Nestlé Perú S.A.',            '994567890', 'ventas@nestle.com.pe'),
('20100070970', 'Laive S.A.',                  '976543210', 'pedidos@laive.com.pe'),
('20503840121', 'Industrias Teal S.A.',        '965432109', 'ventas@teal.com.pe'),
('20601234567', 'Distribuidora Norte E.I.R.L.','954321098', 'contacto@distnorte.pe');


-- ==========================================
-- 4. INSERTAR PRODUCTOS
-- ==========================================
INSERT INTO producto (codigo, nombre, fecha_creacion, id_categoria, stock_actual, unidad_medida, stock_minimo, precio_costo, precio_venta, descripcion) VALUES
-- Abarrotes (cat 1)
('PROD-ABA-001', 'Arroz Costeño Extra 5Kg',        '2026-04-28', 1,  120, 'Saco',    20, 3.50,  4.80,  'Arroz blanco extra grano largo clasificado'),
('PROD-ABA-002', 'Azúcar Rubia Cartavio 5kg',       '2026-04-28', 1,   80, 'Bolsa',   15, 4.20,  5.80,  'Azúcar rubia de caña, presentación familiar'),
('PROD-ABA-003', 'Fideos Lavaggi Espagueti 500g',   '2026-04-28', 1,  130, 'Paquete', 25, 1.80,  2.80,  'Fideos de trigo semolado, corte espagueti'),
('PROD-ABA-004', 'Aceite Primor 1L',                '2026-04-28', 1,   95, 'Botella', 18, 5.60,  7.90,  'Aceite vegetal de girasol, botella 1 litro'),
('PROD-ABA-005', 'Sal Marina Emsal 1kg',            '2026-04-28', 1,  200, 'Bolsa',   30, 0.90,  1.50,  'Sal de mesa yodada y fluorada'),
-- Lácteos y Huevos (cat 2)
('PROD-LAC-001', 'Leche Evaporada Gloria 400g',     '2026-04-28', 2,  250, 'Lata',    48, 3.20,  4.20,  'Leche evaporada entera clásica etiqueta azul'),
('PROD-LAC-002', 'Yogur Gloria Fresa 1L',           '2026-04-28', 2,   60, 'Botella', 10, 4.50,  6.20,  'Yogur bebible sabor fresa con cultivos activos'),
('PROD-LAC-003', 'Queso Fresco Laive 500g',         '2026-04-28', 2,   40, 'Unidad',   8, 7.50, 10.50,  'Queso fresco pasteurizado, textura suave'),
('PROD-LAC-004', 'Mantequilla Laive 200g',          '2026-04-28', 2,   55, 'Unidad',  10, 5.20,  7.50,  'Mantequilla sin sal en barra, 200 gramos'),
('PROD-LAC-005', 'Huevos Blancos Redondos x30',     '2026-04-28', 2,   70, 'Bandeja', 15, 14.00, 19.00, 'Bandeja de 30 huevos blancos frescos categoría A'),
-- Bebidas y Licores (cat 3)
('PROD-BEB-001', 'Gaseosa Inca Kola 3L',            '2026-04-28', 3,   60, 'Botella', 12, 8.50, 11.50,  'Bebida gaseosa sabor original, envase no retornable'),
('PROD-BEB-002', 'Agua San Luis 625ml',             '2026-04-28', 3,  200, 'Botella', 30, 0.70,  1.20,  'Agua mineral sin gas, presentación personal'),
('PROD-BEB-003', 'Jugo Pulp Durazno 1L',            '2026-04-28', 3,   85, 'Caja',    15, 4.00,  5.80,  'Néctar de durazno con pulpa real'),
('PROD-BEB-004', 'Cerveza Cristal Lata 355ml',      '2026-04-28', 3,  180, 'Unidad',  24, 3.80,  5.50,  'Cerveza rubia tipo lager, lata 355 ml'),
-- Snacks y Confitería (cat 4)
('PROD-SNA-001', 'Galletas Casino Menta',           '2026-04-28', 4,  100, 'Paquete', 15, 0.80,  1.30,  'Galletas dulces rellenas con crema sabor a menta'),
('PROD-SNA-002', 'Chifles Inca´s Food 100g',        '2026-04-28', 4,  150, 'Bolsa',   20, 1.50,  2.50,  'Snack de plátano verde frito con sal'),
('PROD-SNA-003', 'Chocolate Sublime 32g',           '2026-04-28', 4,  220, 'Unidad',  30, 1.20,  1.90,  'Chocolate con leche y maní tostado'),
('PROD-SNA-004', 'Chicle Trident Menta x12',        '2026-04-28', 4,  180, 'Caja',    24, 0.60,  1.00,  'Chicles sin azúcar sabor menta, display 12 unidades'),
-- Limpieza y Hogar (cat 6)
('PROD-LIM-001', 'Detergente Ariel 1kg',            '2026-04-28', 6,   45, 'Bolsa',   10, 10.00, 14.50, 'Detergente en polvo para ropa blanca y de color'),
('PROD-LIM-002', 'Lavavajillas Ayudín Limón 500g',  '2026-04-28', 6,   90, 'Unidad',  12, 3.80,  5.50,  'Crema lavavajillas con fragancia a limón'),
('PROD-LIM-003', 'Lejía Clorox 1L',                 '2026-04-28', 6,   75, 'Botella', 15, 3.10,  4.80,  'Lejía desinfectante concentrada aroma pino'),
('PROD-LIM-004', 'Papel Higiénico Suave x4',        '2026-04-28', 6,  110, 'Paquete', 20, 4.50,  6.50,  'Paquete de 4 rollos doble hoja, suave y resistente'),
-- Cuidado Personal (cat 7)
('PROD-CUI-001', 'Shampoo Pantene 400ml',           '2026-04-28', 7,   60, 'Frasco',  10, 12.00, 17.50, 'Shampoo pro-vitaminas para cabello dañado'),
('PROD-CUI-002', 'Jabón Dove 90g',                  '2026-04-28', 7,  140, 'Unidad',  20, 2.50,  3.80,  'Jabón de tocador con crema hidratante'),
('PROD-CUI-003', 'Pasta Dental Colgate 75ml',       '2026-04-28', 7,   95, 'Tubo',    15, 3.90,  5.80,  'Crema dental triple acción, blanqueadora'),
-- Embutidos y Fríos (cat 8)
('PROD-EMB-001', 'Jamonada San Fernando 500g',      '2026-04-28', 8,   50, 'Unidad',   8, 9.50, 13.50,  'Jamonada de cerdo en rodajas, refrigerada'),
('PROD-EMB-002', 'Hot Dog Braedt x10',              '2026-04-28', 8,   65, 'Paquete', 10, 8.00, 11.50,  'Salchichas de pollo tipo hot dog, pack 10 unidades'),
-- Conservas y Enlatados (cat 10)
('PROD-CON-001', 'Atún Florida en Agua 170g',       '2026-04-28', 10, 160, 'Lata',    25, 4.20,  6.00,  'Atún en trozos conservado en agua y sal'),
('PROD-CON-002', 'Sardinas A-1 en Tomate 425g',     '2026-04-28', 10,  90, 'Lata',    15, 3.50,  5.20,  'Sardinas en salsa de tomate, lata grande'),
('PROD-CON-003', 'Frijoles Bayos Bell´s 400g',      '2026-04-28', 10, 120, 'Lata',    20, 2.80,  4.20,  'Frijoles bayos precocidos en lata'),
-- Aceites y Condimentos (cat 11)
('PROD-ACE-001', 'Vinagre Vegetal Tari 500ml',      '2026-04-28', 11,  80, 'Botella', 12, 2.10,  3.50,  'Vinagre blanco de fermentación vegetal'),
('PROD-ACE-002', 'Kétchup A-1 500g',                '2026-04-28', 11, 100, 'Frasco',  15, 4.00,  6.00,  'Salsa de tomate tipo kétchup, presentación familiar');


-- ==========================================
-- 5. INSERTAR MOVIMIENTOS
-- ==========================================

-- MOV 1 | Ingreso | Alicorp | 2026-04-28 | total: 390.00
INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Ingreso', '2026-04-28', 'Compra de mercadería semanal', 'Almacén Principal', 'Ingreso con guía de remisión 001-456. Todo conforme.', 1, 1, 390.00);

-- MOV 2 | Salida | interno | 2026-04-29 | total: 640.00
INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Salida', '2026-04-29', 'Reabastecimiento urgente', 'Almacén Frío', 'Lote con vencimiento a 6 meses.', 6, NULL, 640.00);

-- MOV 3 | Ingreso | Alicorp | 2026-04-10 | total: 650.00
INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Ingreso', '2026-04-10', 'Compra', 'Almacén Principal', 'Factura F001-998', 2, 1, 650.00);

-- MOV 4 | Ingreso | Gloria | 2026-04-12 | total: 156.00
INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Ingreso', '2026-04-12', 'Compra', 'Almacén Frío', 'Ingreso de lácteos por falta de stock', 7, 2, 156.00);

-- MOV 5 | Salida | interno | 2026-04-15 | total: 40.00
INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Salida', '2026-04-15', 'Despacho', 'Tienda Sede Centro', 'Traslado matutino', 3, NULL, 40.00);

-- MOV 6 | Salida | interno | 2026-04-18 | total: 11.70
INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Salida', '2026-04-18', 'Merma', 'Desechos', 'Cajas abolladas', 8, NULL, 11.70);

-- MOV 7 | Ingreso | Alicorp | 2026-04-25 | total: 90.00
INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Ingreso', '2026-04-25', 'Devolución', 'Almacén Principal', 'La tienda devolvió stock', 4, 1, 90.00);

-- MOV 8 | Ingreso | P&G | 2026-05-01 | total: 215.50
INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Ingreso', '2026-05-01', 'Compra de insumos de limpieza', 'Almacén Principal', 'Factura F002-1045. Productos recibidos en buen estado.', 9, 3, 215.50);

-- MOV 9 | Salida | interno | 2026-05-01 | total: 327.92
INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Salida', '2026-05-01', 'Despacho a sucursal', 'Tienda Sede Norte', 'Traslado vespertino autorizado por supervisor.', 5, NULL, 327.92);

-- MOV 10 | Ingreso | Gloria | 2026-04-30 | total: 174.60
INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Ingreso', '2026-04-30', 'Reposición de stock', 'Almacén Frío', 'Ingreso de lácteos y embutidos. Guía de remisión 003-789.', 10, 2, 174.60);

-- MOV 11 | Ingreso | Nestlé | 2026-05-02 | total: 426.40
-- Ingreso de snacks, chocolates y lácteos Nestlé
INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Ingreso', '2026-05-02', 'Compra mensual Nestlé', 'Almacén Principal', 'Factura F003-201. Recibido por almacenero turno mañana.', 1, 5, 426.40);

-- MOV 12 | Salida | interno | 2026-05-02 | total: 183.80
-- Despacho de conservas y condimentos a tienda
INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Salida', '2026-05-02', 'Despacho tienda', 'Tienda Sede Sur', 'Traslado con nota de salida NS-045.', 6, NULL, 183.80);

-- MOV 13 | Ingreso | Laive | 2026-05-03 | total: 511.00
-- Reposición de embutidos, quesos y mantequilla
INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Ingreso', '2026-05-03', 'Reposición embutidos y lácteos', 'Almacén Frío', 'Guía de remisión 004-112. Temperatura de cadena de frío verificada.', 2, 6, 511.00);

-- MOV 14 | Salida | interno | 2026-05-04 | total: 96.50
-- Merma por productos vencidos detectados en revisión
INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Salida', '2026-05-04', 'Merma por vencimiento', 'Desechos', 'Revisión semanal: yogures y jamonada vencidos retirados.', 8, NULL, 96.50);

-- MOV 15 | Ingreso | Dist. Norte | 2026-05-05 | total: 348.00
-- Ingreso de bebidas y snacks de distribuidor regional
INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Ingreso', '2026-05-05', 'Compra bebidas y snacks', 'Almacén Principal', 'Factura F004-089. Productos verificados contra orden de compra OC-031.', 3, 8, 348.00);

-- MOV 16 | Salida | interno | 2026-05-05 | total: 261.00
-- Despacho a tienda sede centro (reabastecimiento semanal)
INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Salida', '2026-05-05', 'Despacho semanal', 'Tienda Sede Centro', 'Traslado programado. Autorizado por administrador.', 4, NULL, 261.00);

-- MOV 17 | Ingreso | P&G | 2026-05-06 | total: 294.00
-- Compra de cuidado personal y limpieza
INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Ingreso', '2026-05-06', 'Compra cuidado personal y limpieza', 'Almacén Principal', 'Factura F005-310. Recibido conforme con orden OC-035.', 5, 3, 326.00);

-- MOV 18 | Salida | interno | 2026-05-07 | total: 156.00
-- Devolución de productos en mal estado al proveedor Alicorp
INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Salida', '2026-05-07', 'Devolución a proveedor', 'Alicorp S.A.A.', 'Fideos y arroz con envases rotos devueltos según acuerdo comercial.', 9, 1, 156.00);

-- MOV 19 | Ingreso | Backus | 2026-05-07 | total: 684.00
-- Ingreso de cerveza y bebidas Backus
INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Ingreso', '2026-05-07', 'Compra bebidas Backus', 'Almacén Principal', 'Factura F006-478. Cajas de cerveza y gaseosas recibidas en buen estado.', 1, 4, 685.50);

-- MOV 20 | Salida | interno | 2026-05-08 | total: 319.00
-- Despacho general a tienda sede norte
INSERT INTO movimiento (tipo_movimiento, fecha_movimiento, motivo, destino, observaciones, id_usuario, id_proveedor, total_movimiento)
VALUES ('Salida', '2026-05-08', 'Despacho a sucursal', 'Tienda Sede Norte', 'Traslado con guía interna GI-022. Conforme.', 7, NULL, 330.50);


-- ==========================================
-- 6. INSERTAR DETALLES DE MOVIMIENTO
-- ==========================================

-- ==========================================
-- MOVIMIENTO 1 | Ingreso | total: 390.00
-- ==========================================
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (1, 1, 100, 3.50, 350.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (1, 15, 50,  0.80,  40.00);

-- ==========================================
-- MOVIMIENTO 2 | Salida | total: 640.00
-- ==========================================
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (2, 6, 200, 3.20, 640.00);

-- ==========================================
-- MOVIMIENTO 3 | Ingreso | total: 650.00
-- ==========================================
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (3, 1,  50, 7.00, 350.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (3, 6, 100, 3.00, 300.00);

-- ==========================================
-- MOVIMIENTO 4 | Ingreso | total: 156.00
-- ==========================================
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (4, 11, 40, 3.90, 156.00);

-- ==========================================
-- MOVIMIENTO 5 | Salida | total: 40.00
-- ==========================================
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (5, 15, 10, 4.00,  40.00);

-- ==========================================
-- MOVIMIENTO 6 | Salida-Merma | total: 11.70
-- ==========================================
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (6, 11,  3, 3.90,  11.70);

-- ==========================================
-- MOVIMIENTO 7 | Ingreso-Devolución | total: 90.00
-- ==========================================
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (7, 19, 30, 3.00,  90.00);

-- ==========================================
-- MOVIMIENTO 8 | Ingreso | total: 215.50
-- ==========================================
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (8, 20, 30, 3.80, 114.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (8,  2, 20, 4.20,  84.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (8,  3, 10, 1.75,  17.50);

-- ==========================================
-- MOVIMIENTO 9 | Salida | total: 328.00
-- ==========================================
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (9,  6,  50, 3.20, 160.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (9, 12,  80, 0.70,  56.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (9,  1,  16, 3.50,  56.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (9, 16,  24, 2.33,  56.00);

-- ==========================================
-- MOVIMIENTO 10 | Ingreso | total: 174.80
-- ==========================================
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (10,  7, 12, 4.50,  54.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (10,  8,  8, 7.50,  60.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (10, 11,  4, 3.90,  15.60);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (10,  7,  5, 4.50,  22.50);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (10,  8,  3, 7.50,  22.50);

-- ==========================================
-- MOVIMIENTO 11 | Ingreso Nestlé | total: 426.00
-- ==========================================
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (11, 17, 150, 1.20, 180.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (11, 15, 100, 0.80,  80.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (11,  6,  52, 3.20, 166.40);

-- ==========================================
-- MOVIMIENTO 12 | Salida tienda sur | total: 183.50
-- ==========================================
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (12, 28, 15, 4.20,  63.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (12, 30, 20, 2.80,  56.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (12, 31,  8, 2.10,  16.80);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (12, 32, 12, 4.00,  48.00);

-- ==========================================
-- MOVIMIENTO 13 | Ingreso Laive | total: 510.00
-- ==========================================
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (13, 26, 30, 9.50,  285.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (13, 27, 25, 8.00,  200.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (13,  9,  5, 5.20,   26.00);

-- ==========================================
-- MOVIMIENTO 14 | Salida-Merma vencidos | total: 95.40
-- ==========================================
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (14,  7,  8, 4.50,  36.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (14, 26,  4, 9.50,  38.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (14,  8,  3, 7.50,  22.50);

-- ==========================================
-- MOVIMIENTO 15 | Ingreso Dist. Norte | total: 348.00
-- ==========================================
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (15, 14,  60, 3.80, 228.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (15, 13,  30, 4.00, 120.00);

-- ==========================================
-- MOVIMIENTO 16 | Salida semanal tienda centro | total: 261.00
-- ==========================================
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (16,  1,  30, 3.50, 105.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (16,  6,  25, 3.20,  80.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (16, 17,  40, 1.20,  48.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (16, 18,  28, 1.00,  28.00);

-- ==========================================
-- MOVIMIENTO 17 | Ingreso P&G | total: 294.00
-- ==========================================
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (17, 23, 10, 12.00, 120.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (17, 24, 40,  2.50, 100.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (17, 25, 10,  3.90,  39.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (17, 21, 10,  3.10,  31.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (17, 22,  8,  4.50,  36.00);

-- ==========================================
-- MOVIMIENTO 18 | Salida-Devolución proveedor | total: 156.00
-- ==========================================
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (18,  3, 40, 1.80,  72.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (18,  1, 12, 3.50,  42.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (18,  2, 10, 4.20,  42.00);

-- ==========================================
-- MOVIMIENTO 19 | Ingreso Backus | total: 684.00
-- ==========================================
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (19, 14, 120, 3.80, 456.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (19, 11,  27, 8.50, 229.50);

-- ==========================================
-- MOVIMIENTO 20 | Salida tienda norte | total: 319.00
-- ==========================================
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (20, 14,  30, 3.80, 114.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (20, 28,  20, 4.20,  84.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (20, 26,   7, 9.50,  66.50);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (20, 27,   6, 8.00,  48.00);
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_unitario, subtotal) VALUES (20,  5,  20, 0.90,  18.00);