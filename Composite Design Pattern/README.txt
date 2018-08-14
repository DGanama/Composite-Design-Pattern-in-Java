A JavaFX Project implementing Composite Design Pattern

The GradenLayout class contains most of the JavaFX interface logic.

GradenComponent is the interface that the components must implement.

FlowerBedComposite presents the composite object and contains FlowerLeafNode objects.

FlowerLeafNode is the Leaf component that could instantiated on its own or as part of the composite object, FlowerBedComposite.

FlowerBedComposite and FlowerLeafNode implement their own verion of the move(double x, double y) method.

FlowerLeafNode classes control their own location through the move method.

FlowerBedComposite classes control their own location and the locations of Leaf Nodes contained within their component lists through their implementation of the move method.
