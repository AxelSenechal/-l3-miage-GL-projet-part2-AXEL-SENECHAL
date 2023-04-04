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
public interface FxRobotInterfacePointAndPointQuery {

    //-----------------------------------------------------------------------------------------------------------

    /**
     * Stores the given position as the position to be used in all {@code point()}-related methods
     * such as {@link #point(Node)} and {@link #point(Point2D)}, and returns itself for method chaining.
     * The default value is {@link Pos#CENTER}
     */
    FxRobot targetPos(Pos pointPosition);

    /**
     * Calls {@link org.testfx.service.locator.PointLocator#point(Point2D)} using {@code new Point2D(x, y)} and sets
     * the {@code PointQuery}'s {@link PointQuery#getPosition()} to {@link FxRobotContext#getPointPosition()}.
     */
    PointQuery point(double x, double y);

    /**
     * Calls {@link org.testfx.service.locator.PointLocator#point(Point2D)} and sets the {@code PointQuery}'s
     * {@link PointQuery#getPosition()} to {@link FxRobotContext#getPointPosition()}.
     */
    PointQuery point(Point2D point);

    /**
     * Calls {@link org.testfx.service.locator.PointLocator#point(Bounds)} and sets the {@code PointQuery}'s
     * {@link PointQuery#getPosition()} to {@link FxRobotContext#getPointPosition()}.
     */
    PointQuery point(Bounds bounds);

    /**
     * Calls {@link org.testfx.service.locator.PointLocator#point(Node)} and sets the {@code PointQuery}'s
     * {@link PointQuery#getPosition()} to {@link FxRobotContext#getPointPosition()}.
     */
    PointQuery point(Node node);

    /**
     * Calls {@link org.testfx.service.locator.PointLocator#point(Scene)} and sets the {@code PointQuery}'s
     * {@link PointQuery#getPosition()} to {@link FxRobotContext#getPointPosition()}.
     */
    PointQuery point(Scene scene);

    /**
     * Calls {@link org.testfx.service.locator.PointLocator#point(Window)} and sets the {@code PointQuery}'s
     * {@link PointQuery#getPosition()} to {@link FxRobotContext#getPointPosition()}.
     */
    PointQuery point(Window window);

    /**
     * Convenience method: Tries to find a given node via {@link #lookup(String)} before calling {@link #point(Node)},
     * throwing a {@link FxRobotException} if no node is found.
     */
    PointQuery point(String query);

    /**
     * Convenience method: Tries to find a given node via {@link #lookup(Matcher)} before calling {@link #point(Node)},
     * throwing a {@link FxRobotException} if no node is found.
     */
    <T extends Node> PointQuery point(Matcher<T> matcher);

    /**
     * Convenience method: Tries to find a given node via {@link #lookup(Predicate)} before calling
     * {@link #point(Node)}, throwing a {@link FxRobotException} if no node is found.
     */
    <T extends Node> PointQuery point(Predicate<T> predicate);

    /**
     * Convenience method: Calls {@link #point(Point2D)} and sets the query's offset by the given offset values.
     */
    PointQuery offset(Point2D point, double offsetX, double offsetY);

    /**
     * Convenience method: Calls {@link #point(Point2D)} and sets the query's offset by the given offset point
     * (where the point's x-component is the offsetX, and the point's y-component is the offsetY).
     */
    default PointQuery offset(Point2D point, Point2D offset) {
        return offset(point, offset.getX(), offset.getY());
    }

    /**
     * Convenience method: Calls {@link #point(Bounds)} and sets the query's offset by the given offset values.
     */
    PointQuery offset(Bounds bounds, double offsetX, double offsetY);

    /**
     * Convenience method: Calls {@link #point(Bounds)} and sets the query's offset by the given offset point
     * (where the point's x-component is the offsetX, and the point's y-component is the offsetY).
     */
    default PointQuery offset(Bounds bounds, Point2D offset) {
        return offset(bounds, offset.getX(), offset.getY());
    }

    /**
     * Convenience method: Calls {@link #point(Node)} and sets the query's offset by the given offset values.
     */
    PointQuery offset(Node node, double offsetX, double offsetY);

    /**
     * Convenience method: Calls {@link #point(Node)} and sets the query's offset by the given offset point
     * (where the point's x-component is the offsetX, and the point's y-component is the offsetY).
     */
    default PointQuery offset(Node node, Point2D offset) {
        return offset(node, offset.getX(), offset.getY());
    }

    /**
     * Convenience method: Calls {@link #point(Node)} and sets the query's offset by the given offset values
     * where the offset is computed with respect to the given offset reference position.
     */
    PointQuery offset(Node node, Pos offsetReferencePos, double offsetX, double offsetY);

    /**
     * Convenience method: Calls {@link #point(Node)} and sets the query's offset by the given offset point
     * (where the point's x-component is the offsetX, and the point's y-component is the offsetY) where the
     * offset is computed with respect to the given offset reference position.
     */
    default PointQuery offset(Node node, Pos offsetReferencePos, Point2D offset) {
        return offset(node, offsetReferencePos, offset.getX(), offset.getY());
    }

    /**
     * Convenience method: Calls {@link #point(Scene)} and sets the query's offset by the given offset values.
     */
    PointQuery offset(Scene scene, double offsetX, double offsetY);

    /**
     * Convenience method: Calls {@link #point(Scene)} and sets the query's offset by the given offset offset point
     * (where the point's x-component is the offsetX, and the point's y-component is the offsetY).
     */
    default PointQuery offset(Scene scene, Point2D offset) {
        return offset(scene, offset.getX(), offset.getY());
    }

    /**
     * Convenience method: Calls {@link #point(Window)} and sets the query's offset by the given offset values.
     */
    PointQuery offset(Window window, double offsetX, double offsetY);

    /**
     * Convenience method: Calls {@link #point(Window)} and sets the query's offset by the given offset point
     * (where the point's x-component is the offsetX, and the point's y-component is the offsetY).
     */
    default PointQuery offset(Window window, Point2D offset) {
        return offset(window, offset.getX(), offset.getY());
    }

    /**
     * Convenience method: Calls {@link #point(String)} and sets the query's offset by the given offset values.
     */
    PointQuery offset(String query, double offsetX, double offsetY);

    /**
     * Convenience method: Calls {@link #point(String)} and sets the query's offset by the given offset point
     * (where the point's x-component is the offsetX, and the point's y-component is the offsetY).
     */
    default PointQuery offset(String query, Point2D offset) {
        return offset(query, offset.getX(), offset.getY());
    }

    /**
     * Convenience method: Calls {@link #point(Matcher)} and sets the query's offset by the given offset values.
     */
    <T extends Node> PointQuery offset(Matcher<T> matcher, double offsetX, double offsetY);

    /**
     * Convenience method: Calls {@link #point(Matcher)} and sets the query's offset by the given offset point
     * (where the point's x-component is the offsetX, and the point's y-component is the offsetY).
     */
    default <T extends Node> PointQuery offset(Matcher<T> matcher, Point2D offset) {
        return offset(matcher, offset.getX(), offset.getY());
    }

    /**
     * Convenience method: Calls {@link #point(Predicate)} and sets the query's offset by the given offset values.
     */
    <T extends Node> PointQuery offset(Predicate<T> predicate, double offsetX, double offsetY);

    /**
     * Convenience method: Calls {@link #point(Predicate)} and sets the query's offset by the given offset point
     * (where the point's x-component is the offsetX, and the point's y-component is the offsetY).
     */
    default <T extends Node> PointQuery offset(Predicate<T> predicate, Point2D offset) {
        return offset(predicate, offset.getX(), offset.getY());
    }

    //-----------------------------------------------------------------------------------------------------------

}
//-----------------------------------------------------------------------------------------------------------
