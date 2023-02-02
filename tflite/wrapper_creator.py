


"""
run this first to install the library:

pip3 install tflite-support

then run this second:

tflite_codegen --model=./model.tflite \
    --package_name=org.tensorflow.lite.detector \
    --model_class_name=ObjectDetectModel \
    --destination=./detector
"""