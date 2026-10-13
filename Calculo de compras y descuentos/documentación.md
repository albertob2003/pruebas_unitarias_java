# Documento Técnico: Módulo de Cálculo de Compras (`PurchaseCalculator`)

---

## 1. Descripción del problema

El sistema de comercio electrónico requiere un módulo centralizado capaz de procesar transacciones comerciales calculando subtotales, validando la viabilidad de la compra, aplicando políticas de descuento por volumen y determinando el precio final a pagar por el cliente.

Es fundamental verificar de forma automatizada estos cálculos para evitar errores en el cobro a los usuarios, prevenir pérdidas financieras por fallas aritméticas y garantizar que las reglas de negocio se apliquen de manera consistente y predecible ante cualquier escenario de entrada.

---

## 2. Reglas de negocio

* **Cálculo de Subtotal:** El subtotal se obtiene multiplicando directamente el precio unitario del producto por la cantidad de unidades solicitadas (`price * quantity`).
* **Validación de Compra:** Una transacción se considera válida (`true`) únicamente si tanto el precio como la cantidad son estrictamente mayores a cero. Cualquier valor negativo o nulo invalida la operación.
* **Aplicación de Descuento:** Se otorga un **10% de descuento** sobre el subtotal cuando este es igual o superior a `$1,000.0`. Si el subtotal es menor a dicho umbral, el descuento aplicado es de `$0.0`.
* **Precio Final:** Se calcula restando el descuento obtenido al subtotal original. Si no aplica descuento, el precio final es idéntico al subtotal.

---

## 3. Casos de prueba

La siguiente tabla detalla los casos de prueba unitarios implementados para verificar cada uno de los métodos de la clase `PurchaseCalculator`:

| Método Probado | Datos de Entrada (`price, quantity` o `subtotal`) | Resultado Esperado | Resultado Obtenido | Propósito del Caso |
| --- | --- | --- | --- | --- |
| `CalculateSubtotal` | `100.0, 3` | `300.0` | `300.0` | Verificar la multiplicación correcta de precio por cantidad estándar. |
| `CalculateSubtotal` | `100.0, 1` | `100.0` | `100.0` | Comprobar el comportamiento con una cantidad unitaria. |
| `CalculateSubtotal` | `0.0, 3` | `0.0` | `0.0` | Validar el cálculo cuando el precio es cero. |
| `isValidPurchase` | `12.0, 8` | `true` | `true` | Validar una compra con valores positivos estándar. |
| `isValidPurchase` | `0.0, 8` | `false` | `false` | Comprobar el rechazo de la compra si el precio es cero. |
| `isValidPurchase` | `12.0, 0` | `false` | `false` | Comprobar el rechazo de la compra si la cantidad es cero. |
| `isValidPurchase` | `-60.0, 8` | `false` | `false` | Comprobar el rechazo de la compra ante valores de precio negativos. |
| `calculateDiscount` | `38.6` | `0.0` | `0.0` | Verificar que no se otorgue descuento por debajo del umbral de `$1,000`. |
| `calculateDiscount` | `1000.0` | `100.0` | `100.0` | Verificar la aplicación exacta del 10% en el límite inferior del umbral. |
| `calculateDiscount` | `1200.0` | `120.0` | `120.0` | Verificar el cálculo correcto del 10% superando el umbral. |
| `calculateFinalPrice` | `900.0` | `900.0` | `900.0` | Asegurar que el precio final no varíe si no aplica descuento. |
| `calculateFinalPrice` | `1200.0` | `1080.0` | `1080.0` | Verificar la correcta aplicación de la resta entre el subtotal y su descuento. |
| `calculateFinalPrice` | `1000.0` | `900.0` | `900.0` | Comprobar el precio final aplicando el descuento exactamente en el límite. |

---

## 4. Resultados de ejecución

* **Total de pruebas ejecutadas:** 4 métodos de prueba agrupando 13 afirmaciones individuales (`assertions`).
* **Pruebas exitosas (Passed):** 13 (100%).
* **Pruebas fallidas (Failed):** 0 (0%).
* **Estado general:** La suite de pruebas pasó de forma íntegra, confirmando que la lógica implementada responde correctamente a los requerimientos funcionales y comerciales definidos.

---

## 5. Errores que se pueden prevenir

La implementación de estas pruebas unitarias permite prevenir y detectar de forma temprana los siguientes problemas comunes:

1. **Cobros erróneos por datos inválidos:** Previene que transacciones con cantidades o precios negativos/ceros alteren las finanzas o generen anomalías en el sistema mediante la validación estricta en `isValidPurchase`.
2. **Fallos en la aplicación de umbrales comerciales:** Detecta errores lógicos en el cálculo de promociones (como aplicar mal el límite de los `$1,000` o calcular porcentajes erróneos en los bordes del descuento), asegurando que el cliente reciba el beneficio correcto y la empresa mantenga su margen esperado.

---

## 6. Conclusión

Las pruebas unitarias son un pilar indispensable para garantizar la confiabilidad y robustez de una aplicación de comercio electrónico. Al automatizar la verificación de componentes críticos como cálculos de precios, validaciones de transacciones y reglas de descuento, los desarrolladores pueden detectar errores lógicos de manera inmediata antes de que lleguen a producción. Esto reduce drásticamente el riesgo de pérdidas económicas, mejora la experiencia del usuario final y facilita el mantenimiento y escalabilidad del código a largo plazo.