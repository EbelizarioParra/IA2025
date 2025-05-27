
# Ebelizario Parra Martínez
# Regresión lineal: Modelar la relación entre variables independientes (X) y dependiente (Y) mediante una línea recta

import numpy as np
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression

# Datos de entrada y salida
x_valores = np.array([1, 2, 3, 4, 5]).reshape(-1, 1)
y_valores = np.array([1.5, 3.0, 4.5, 6.0, 7.5])

# Inicialización y entrenamiento del modelo
regresion = LinearRegression()
regresion.fit(x_valores, y_valores)

# Generar predicciones
y_pred = regresion.predict(x_valores)

# Mostrar parámetros del modelo
print(f"Coeficiente (pendiente): {regresion.coef_[0]}")
print(f"Intersección: {regresion.intercept_}")

# Visualización del resultado
plt.figure(figsize=(8, 6))
plt.scatter(x_valores, y_valores, color='blue', label='Datos originales')
plt.plot(x_valores, y_pred, color='red', label='Línea de regresión')
plt.title('Regresión Lineal')
plt.xlabel('Variable X')
plt.ylabel('Variable Y')
plt.legend()
plt.grid(True)
plt.show()
