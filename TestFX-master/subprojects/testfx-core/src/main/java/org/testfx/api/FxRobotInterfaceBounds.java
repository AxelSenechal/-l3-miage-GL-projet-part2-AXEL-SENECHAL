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
public interface FxRobotInterfaceBounds {


    /**
     * Calls {@link org.testfx.util.BoundsQueryUtils#bounds(double, double, double, double)}
     */
    BoundsQuery bounds(double minX, double minY, double width, double height);

    /**
     * Calls {@link org.testfx.util.BoundsQueryUtils#bounds(Point2D)}
     */
    BoundsQuery bounds(Point2D point);

    /**
     * Creates a {@code BoundsQuery} that returns the given bounds
     */
    BoundsQuery bounds(Bounds bounds);

    /**
     * Calls {@link org.testfx.util.BoundsQueryUtils#boundsOnScreen(Node)}
     */
    BoundsQuery bounds(Node node);

    /**
     * Calls {@link org.testfx.util.BoundsQueryUtils#boundsOnScreen(Bounds, Scene)} with given scene's bounds
     */
    BoundsQuery bounds(Scene scene);

    /**
     * Calls {@link org.testfx.util.BoundsQueryUtils#boundsOnScreen(Bounds, Window)} with the given window's bounds
     */
    BoundsQuery bounds(Window window);

    /**
     * NOT YET IMPLEMENTED
     */
    BoundsQuery bounds(String query);

    /**
     * NOT YET IMPLEMENTED
     */
    <T extends Node> BoundsQuery bounds(Matcher<T> matcher);

    /**
     * NOT YET IMPLEMENTED
     */
    <T extends Node> BoundsQuery bounds(Predicate<T> predicate);


}

