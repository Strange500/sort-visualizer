package fr.lille.br.sort.visualizer.vue;

import javafx.scene.canvas.Canvas;

import java.lang.reflect.InvocationTargetException;

public class ResizableCanvas extends Canvas {

    @Override
    public double minHeight(double width) {
        return 64;
    }

    @Override
    public double maxHeight(double width) {
        return 10000;
    }

    @Override
    public double prefHeight(double width) {
        return minHeight(width);
    }

    @Override
    public double minWidth(double height) {
        return 0;
    }

    @Override
    public double maxWidth(double height) {
        return 10000;
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public void resize(double width, double height) {
        super.setWidth(width);
        super.setHeight(height);
        try {
            CanvasController.instance.draw();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }
}
