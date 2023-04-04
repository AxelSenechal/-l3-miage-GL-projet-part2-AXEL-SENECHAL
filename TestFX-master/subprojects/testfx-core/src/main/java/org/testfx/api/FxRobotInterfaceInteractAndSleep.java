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
public interface FxRobotInterfaceInteractAndSleep {

    //-----------------------------------------------------------------------------------------------------------

    /**
     * Calls a runnable on the FX application thread and waits for it and
     * consecutive events to execute. So changes to the gui triggered by the
     * runnable will be performed when returned from this method.
     *
     * @param runnable the runnable
     * @return this robot
     */
    FxRobot interact(Runnable runnable);

    /**
     * Calls a callable on the FX application thread and waits for it and
     * consecutive events to execute. So changes to the gui triggered by the
     * callable will be performed when returned from this method.
     *
     * @param callable the callable
     * @return this robot
     */
    <T> FxRobot interact(Callable<T> callable);

    /**
     * Calls a runnable on the FX application thread and waits for it to
     * execute. It does not wait for other events on the fx application thread.
     * So changes to the gui triggered by the runnable may not be performed when
     * returned from this method.
     *
     * @param runnable the runnable
     * @return this robot
     */
    FxRobot interactNoWait(Runnable runnable);

    /**
     * Calls a callable on the FX application thread and waits for it to
     * execute. It does not wait for other events on the fx application thread.
     * So changes to the gui triggered by the callable may not be performed when
     * returned from this method.
     *
     * @param callable the callable
     * @return this robot
     */
    <T> FxRobot interactNoWait(Callable<T> callable);

    /**
     * Calls {@link WaitForAsyncUtils#waitForFxEvents()} and returns itself for method chaining.
     */
    FxRobot interrupt();

    /**
     * Calls {@link WaitForAsyncUtils#waitForFxEvents(int)} and returns itself for method chaining.
     */
    FxRobot interrupt(int attemptsCount);

    /**
     * Calls {@link org.testfx.robot.SleepRobot#sleep(long)} and returns itself for more method chaining.
     */
    FxRobot sleep(long milliseconds);

    /**
     * Calls {@link org.testfx.robot.SleepRobot#sleep(long, TimeUnit)} and returns itself for more method chaining.
     */
    FxRobot sleep(long duration, TimeUnit timeUnit);
//-----------------------------------------------------------------------------------------------------------

}

