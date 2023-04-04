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
public interface FxRobotInterfaceWindow {

    /**
     * Calls {@link WindowFinder#targetWindow()} and returns itself for method chaining.
     */
    Window targetWindow();

    /**
     * Calls {@link WindowFinder#targetWindow(Window)} and returns itself for method chaining.
     */
    FxRobot targetWindow(Window window);

    /**
     * Calls {@link WindowFinder#targetWindow(Predicate)} and returns itself for method chaining.
     */
    FxRobot targetWindow(Predicate<Window> predicate);

    // Convenience methods:
    /**
     * Convenience method: Calls {@link WindowFinder#targetWindow(int)} and returns itself for method chaining.
     */
    FxRobot targetWindow(int windowIndex);

    /**
     * Convenience method: Calls {@link WindowFinder#targetWindow(String)} and returns itself for method chaining.
     */
    FxRobot targetWindow(String stageTitleRegex);

    /**
     * Convenience method: Calls {@link WindowFinder#targetWindow(Pattern)} and returns itself for method chaining.
     */
    FxRobot targetWindow(Pattern stageTitlePattern);

    /**
     * Convenience method: Calls {@link WindowFinder#targetWindow(Scene)} and returns itself for method chaining.
     */
    FxRobot targetWindow(Scene scene);

    /**
     * Convenience method: Calls {@link WindowFinder#targetWindow(Node)} and returns itself for method chaining.
     */
    FxRobot targetWindow(Node node);

    /**
     * Calls {@link WindowFinder#listWindows()} ()} and returns itself for method chaining.
     */
    List<Window> listWindows();

    /**
     * Calls {@link WindowFinder#listTargetWindows()} and returns itself for method chaining.
     */
    List<Window> listTargetWindows();

    /**
     * Calls {@link WindowFinder#window(Predicate)} and returns itself for method chaining.
     */
    Window window(Predicate<Window> predicate);

    // Convenience methods:
    /**
     * Convenience method: Calls {@link WindowFinder#window(int)} and returns itself for method chaining.
     */
    Window window(int windowIndex);

    /**
     * Convenience method: Calls {@link WindowFinder#window(String)} and returns itself for method chaining.
     */
    Window window(String stageTitleRegex);

    /**
     * Convenience method: Calls {@link WindowFinder#window(Pattern)} and returns itself for method chaining.
     */
    Window window(Pattern stageTitlePattern);

    /**
     * Convenience method: Calls {@link WindowFinder#window(Scene)} and returns itself for method chaining.
     */
    Window window(Scene scene);

    /**
     * Convenience method: Calls {@link WindowFinder#window(Node)} and returns itself for method chaining.
     */
    Window window(Node node);
//-------------------------------------------------------------------------------------------------------------


}
//-----------------------------------------------------------------------------------------------------------
