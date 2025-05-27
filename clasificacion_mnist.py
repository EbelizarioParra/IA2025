
# Ebelizario Parra Martínez
# Clasificación de dígitos escritos a mano con TensorFlow y MNIST

import tensorflow as tf
import matplotlib.pyplot as plt
import numpy as np
from tensorflow.keras.datasets import mnist
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, Flatten
from tensorflow.keras.utils import to_categorical

# Cargar y preprocesar datos
(train_images, train_labels), (test_images, test_labels) = mnist.load_data()
train_images, test_images = train_images / 255.0, test_images / 255.0
train_labels_cat = to_categorical(train_labels)
test_labels_cat = to_categorical(test_labels)

# Definir modelo secuencial
modelo = Sequential()
modelo.add(Flatten(input_shape=(28, 28)))
modelo.add(Dense(128, activation='relu'))
modelo.add(Dense(10, activation='softmax'))

# Compilar modelo
modelo.compile(optimizer='adam', loss='categorical_crossentropy', metrics=['accuracy'])

# Entrenar modelo
modelo.fit(train_images, train_labels_cat, epochs=5, batch_size=32, validation_split=0.1)

# Evaluar modelo
loss, accuracy = modelo.evaluate(test_images, test_labels_cat)
print(f"Precisión en los datos de prueba: {accuracy:.2f}")

# Visualizar una imagen y su predicción
imagen_prueba = test_images[0]
plt.imshow(imagen_prueba, cmap='gray')
plt.title("Imagen de prueba")
plt.show()

prediccion = modelo.predict(np.expand_dims(imagen_prueba, axis=0))
print(f"Predicción: {np.argmax(prediccion)}")
print(f"Etiqueta real: {test_labels[0]}")
