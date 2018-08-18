A JavaFX Project implementing Composite Design Pattern

The composite design pattern describes a group of objects that is treated the same way as a single instance of the same type of object. The intent of this pattern is to "compose" objects into tree structures to represent part-whole hierarchies. Implementing the composite pattern lets clients treat individual objects and compositions uniformly.

The GradenLayout class contains most of the JavaFX interface logic.

GradenComponent is the interface that the components must implement.

FlowerBedComposite presents the composite object and contains FlowerLeafNode objects.

FlowerLeafNode is the Leaf component that could instantiated on its own or as part of the composite object, FlowerBedComposite.

FlowerBedComposite and FlowerLeafNode implement their own verion of the move(double x, double y) method.

FlowerLeafNode classes control their own location through the move method.

FlowerBedComposite classes control their own location and the locations of Leaf Nodes contained within their component lists through their implementation of the move method.

The Composite design pattern is helpful when:
  - A part-whole hierarchy should be represented so that clients can treat part and whole objects uniformly.
  - A part-whole hierarchy should be represented as tree structure.
