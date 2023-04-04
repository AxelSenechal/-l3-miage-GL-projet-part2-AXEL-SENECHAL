/*
 * Copyright 2013-2014 SmartBear Software
 * Copyright 2014-2021 The TestFX Contributors
 *
 * Licensed under the EUPL, Version 1.1 or - as soon they will be approved by the
 * European Commission - subsequent versions of the EUPL (the "Licence"); You may
 * not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 * http://ec.europa.eu/idabc/eupl.html
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the Licence is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the Licence for the
 * specific language governing permissions and limitations under the Licence.
 */
package org.testfx.api;

import java.net.URL;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import javafx.geometry.Bounds;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VerticalDirection;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.MouseButton;
import javafx.stage.Window;

import org.hamcrest.Matcher;
import org.testfx.robot.Motion;
import org.testfx.service.finder.NodeFinder;
import org.testfx.service.finder.WindowFinder;
import org.testfx.service.query.BoundsQuery;
import org.testfx.service.query.NodeQuery;
import org.testfx.service.query.PointQuery;
import org.testfx.service.support.Capture;
import org.testfx.util.WaitForAsyncUtils;

/**
 * Wrapper-like interface that makes it easier to chain together multiple robot methods while adding a number of
 * convenience methods, such as finding a given node, scene or window via a {@link PointQuery}, a {@link Predicate},
 * or a {@link Matcher}.
 */
public interface FxRobotInterfaceDragAndDropAndMove {


    /**
     * Calls {@link org.testfx.robot.DragRobot#drag(MouseButton...)} and returns itself for more method chaining.
     */
    FxRobot drag(MouseButton... buttons);

    /**
     * Calls {@link org.testfx.robot.DragRobot#drag(PointQuery, MouseButton...)} and returns itself for more method
     * chaining.
     */
    FxRobot drag(PointQuery pointQuery, MouseButton... buttons);

    /**
     * Calls {@link org.testfx.robot.DragRobot#drop()} and returns itself for more method chaining.
     */
    FxRobot drop();

    /**
     * Calls {@link org.testfx.robot.DragRobot#dropTo(PointQuery)} and returns itself for more method chaining.
     */
    FxRobot dropTo(PointQuery pointQuery);

    /**
     * Calls {@link org.testfx.robot.DragRobot#dropBy(double, double)} and returns itself for more method chaining.
     */
    FxRobot dropBy(double x, double y);

    /**
     * Convenience method: Moves mouse to the point returned from {@link #point(double, double)}, presses the given
     * buttons, and returns itself for method chaining.
     */
    FxRobot drag(double x, double y, MouseButton... buttons);
    /**
     * Convenience method: Moves mouse to the point returned from {@link #point(Point2D)}, presses the given
     * buttons, and returns itself for method chaining.
     */
    FxRobot drag(Point2D point, MouseButton... buttons);

    /**
     * Convenience method: Moves mouse to the point returned from {@link #point(Bounds)}, presses the given
     * buttons, and returns itself for method chaining.
     */
    FxRobot drag(Bounds bounds, MouseButton... buttons);

    /**
     * Convenience method: Moves mouse to the point returned from {@link #point(Node)}, presses the given
     * buttons, and returns itself for method chaining.
     */
    FxRobot drag(Node node, MouseButton... buttons);

    /**
     * Convenience method: Moves mouse to the point returned from {@link #point(Scene)}, presses the given
     * buttons, and returns itself for method chaining.
     */
    FxRobot drag(Scene scene, MouseButton... buttons);

    /**
     * Convenience method: Moves mouse to the point returned from {@link #point(Window)}, presses the given
     * buttons, and returns itself for method chaining.
     */
    FxRobot drag(Window window, MouseButton... buttons);

    /**
     * Convenience method: Moves mouse to the point returned from {@link #point(String)}, presses the given
     * buttons, and returns itself for method chaining.
     */
    FxRobot drag(String query, MouseButton... buttons);

    /**
     * Convenience method: Moves mouse to the point returned from {@link #point(Matcher)}, presses the given
     * buttons, and returns itself for method chaining.
     */
    <T extends Node> FxRobot drag(Matcher<T> matcher, MouseButton... buttons);

    /**
     * Convenience method: Moves mouse to the point returned from {@link #point(Predicate)}, presses the given
     * buttons, and returns itself for method chaining.
     */
    <T extends Node> FxRobot drag(Predicate<T> predicate, MouseButton... buttons);

    /**
     * Convenience method: Moves mouse to the point returned from {@link #point(double, double)}, releases the buttons
     * that were pressed in {@link #drag(MouseButton...)}- or {@link #press(MouseButton...)}-related methods,
     * and returns itself for method chaining.
     */
    FxRobot dropTo(double x, double y);

    /**
     * Convenience method: Moves mouse to the point returned from {@link #point(Point2D)}, releases the buttons
     * that were pressed in {@link #drag(MouseButton...)}- or {@link #press(MouseButton...)}-related methods,
     * and returns itself for method chaining.
     */
    FxRobot dropTo(Point2D point);

    /**
     * Convenience method: Moves mouse to the point returned from {@link #point(Bounds)}, releases the buttons
     * that were pressed in {@link #drag(MouseButton...)}- or {@link #press(MouseButton...)}-related methods,
     * and returns itself for method chaining.
     */
    FxRobot dropTo(Bounds bounds);

    /**
     * Convenience method: Moves mouse to the point returned from {@link #point(Node)}, releases the buttons
     * that were pressed in {@link #drag(MouseButton...)}- or {@link #press(MouseButton...)}-related methods,
     * and returns itself for method chaining.
     */
    FxRobot dropTo(Node node);

    /**
     * Convenience method: Moves mouse to the point returned from {@link #point(Scene)}, releases the buttons
     * that were pressed in {@link #drag(MouseButton...)}- or {@link #press(MouseButton...)}-related methods,
     * and returns itself for method chaining.
     */
    FxRobot dropTo(Scene scene);

    /**
     * Convenience method: Moves mouse to the point returned from {@link #point(Window)}, releases the buttons
     * that were pressed in {@link #drag(MouseButton...)}- or {@link #press(MouseButton...)}-related methods,
     * and returns itself for method chaining.
     */
    FxRobot dropTo(Window window);

    /**
     * Convenience method: Moves mouse to the point returned from {@link #point(String)}, releases the buttons
     * that were pressed in {@link #drag(MouseButton...)}- or {@link #press(MouseButton...)}-related methods,
     * and returns itself for method chaining.
     */
    FxRobot dropTo(String query);

    /**
     * Convenience method: Moves mouse to the point returned from {@link #point(Matcher)}, releases the buttons
     * that were pressed in {@link #drag(MouseButton...)}- or {@link #press(MouseButton...)}-related methods,
     * and returns itself for method chaining.
     */
    <T extends Node> FxRobot dropTo(Matcher<T> matcher);

    /**
     * Convenience method: Moves mouse to the point returned from {@link #point(Predicate)}, releases the buttons
     * that were pressed in {@link #drag(MouseButton...)}- or {@link #press(MouseButton...)}-related methods,
     * and returns itself for method chaining.
     */
    <T extends Node> FxRobot dropTo(Predicate<T> predicate);

    /**
     * Calls {@link org.testfx.robot.KeyboardRobot#press(KeyCode...)} and returns itself for method chaining.
     */
    FxRobot press(KeyCode... keys);

    /**
     * Calls {@link org.testfx.robot.KeyboardRobot#press(KeyCode...)} and returns itself for method chaining.
     */
    FxRobot release(KeyCode... keys);

    /**
     * Presses and holds mouse buttons.
     *
     * @param buttons mouse buttons to press, defaults to primary mouse button.
     */
    FxRobot press(MouseButton... buttons);

    /**
     * Releases pressed mouse buttons.
     *
     * @param buttons mouse buttons to release, defaults to all pressed mouse buttons.
     */
    FxRobot release(MouseButton... buttons);

    /**
     * Calls {@link org.testfx.robot.MoveRobot#moveTo(PointQuery)} and returns itself for more method chaining.
     */
    default FxRobot moveTo(PointQuery pointQuery) {
        return moveTo(pointQuery, Motion.DEFAULT);
    }

    /**
     * Calls {@link org.testfx.robot.MoveRobot#moveTo(PointQuery, Motion)} and returns itself for more method chaining.
     */
    FxRobot moveTo(PointQuery pointQuery, Motion motion);

    /**
     * Calls {@link org.testfx.robot.MoveRobot#moveBy(double, double)} and returns itself for more method chaining.
     */
    default FxRobot moveBy(double x, double y) {
        return moveBy(x, y, Motion.DEFAULT);
    }

    /**
     * Calls {@link org.testfx.robot.MoveRobot#moveBy(double, double, Motion)} and returns itself for more method
     * chaining.
     */
    FxRobot moveBy(double x, double y, Motion motion);

    /**
     * Convenience method: Moves mouse directly to the point returned from {@link #point(double, double)} and returns
     * itself for method chaining.
     */
    default FxRobot moveTo(double x, double y) {
        return moveTo(x, y, Motion.DEFAULT);
    }

    /**
     * Convenience method: Moves mouse using the given {@code motion} (see: {@link Motion} to the point returned from
     * {@link #point(double, double)} and returns itself for method chaining.
     */
    FxRobot moveTo(double x, double y, Motion motion);

    /**
     * Convenience method: Moves mouse directly to the point returned from {@link #point(Point2D)} and returns itself
     * for method chaining.
     */
    default FxRobot moveTo(Point2D point) {
        return moveTo(point, Motion.DEFAULT);
    }

    /**
     * Convenience method: Moves mouse using the given {@code motion} (see: {@link Motion} to the point returned from
     * {@link #point(Point2D)} and returns itself for method chaining.
     */
    FxRobot moveTo(Point2D point, Motion motion);

    /**
     * Convenience method: Moves mouse directly to the center of the given {@code Bounds} and returns itself for
     * method chaining.
     */
    default FxRobot moveTo(Bounds bounds) {
        return moveTo(bounds, Motion.DEFAULT);
    }

    /**
     * Convenience method: Moves mouse using the given {@code motion} (see: {@link Motion} to the point returned from
     * {@link #point(Bounds)} and returns itself for method chaining.
     */
    FxRobot moveTo(Bounds bounds, Motion motion);

    /**
     * Convenience method: Moves mouse directly to the center of the given {@code Node} and returns itself
     * for method chaining.
     */
    default FxRobot moveTo(Node node) {
        return moveTo(node, Motion.DEFAULT);
    }

    /**
     * Convenience method: Moves mouse using the given {@code motion} (see: {@link Motion} to the center of the
     * given {@code Node} and returns itself for method chaining.
     */
    default FxRobot moveTo(Node node, Motion motion) {
        return moveTo(node, Pos.CENTER, Point2D.ZERO, motion);
    }

    /**
     * Convenience method: Moves mouse directly to the point returned from {@link #point(Node)},
     * with the given offset from the center of the given {@code Node}, and returns itself for method chaining.
     */
    default FxRobot moveTo(Node node, Point2D offset) {
        return moveTo(node, Pos.CENTER, offset, Motion.DEFAULT);
    }

    /**
     * Convenience method: Moves mouse using the given {@code motion} (see: {@link Motion} to the point returned from
     * {@link #point(Node)}, with the given offset (from the {@code offsetReferencePos}, and returns itself for
     * method chaining.
     */
    FxRobot moveTo(Node node, Pos offsetReferencePos, Point2D offset, Motion motion);

    /**
     * Convenience method: Moves mouse directly to the center of the given {@code Scene} and returns itself
     * for method chaining.
     */
    default FxRobot moveTo(Scene scene) {
        return moveTo(scene, Motion.DEFAULT);
    }

    /**
     * Convenience method: Moves mouse using the given {@code motion} (see: {@link Motion} to the point returned from
     * {@link #point(Scene)} and returns itself for method chaining.
     */
    FxRobot moveTo(Scene scene, Motion motion);

    /**
     * Convenience method: Moves mouse directly to the center of the given {@code Window} and returns itself
     * for method chaining.
     */
    default FxRobot moveTo(Window window) {
        return moveTo(window, Motion.DEFAULT);
    }

    /**
     * Convenience method: Moves mouse using the given {@code motion} (see: {@link Motion} to the point returned from
     * {@link #point(Window)} and returns itself for method chaining.
     */
    FxRobot moveTo(Window window, Motion motion);

    /**
     * Convenience method: Moves mouse directly to the point returned from {@link #point(String)} and returns itself
     * for method chaining.
     */
    default FxRobot moveTo(String query) {
        return moveTo(query, Motion.DEFAULT);
    }

    /**
     * Convenience method: Moves mouse using the given {@code motion} (see: {@link Motion} to the point returned from
     * {@link #point(String)} and returns itself for method chaining.
     */
    FxRobot moveTo(String query, Motion motion);

    /**
     * Convenience method: Moves mouse directly to the point returned from {@link #point(Matcher)} and returns itself
     * for method chaining.
     */
    default <T extends Node> FxRobot moveTo(Matcher<T> matcher) {
        return moveTo(matcher, Motion.DEFAULT);
    }

    /**
     * Convenience method: Moves mouse using the given {@code motion} (see: {@link Motion} to the point returned from
     * {@link #point(Matcher)} and returns itself for method chaining.
     */
    <T extends Node> FxRobot moveTo(Matcher<T> matcher, Motion motion);

    /**
     * Convenience method: Moves mouse directly to the point returned from {@link #point(Predicate)} and returns itself
     * for method chaining.
     */
    default <T extends Node> FxRobot moveTo(Predicate<T> predicate) {
        return moveTo(predicate, Motion.DEFAULT);
    }

    /**
     * Convenience method: Moves mouse using the given {@code motion} (see: {@link Motion} to the point returned from
     * {@link #point(Predicate)} and returns itself for method chaining.
     */
    <T extends Node> FxRobot moveTo(Predicate<T> predicate, Motion motion);

}

