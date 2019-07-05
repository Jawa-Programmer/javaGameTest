/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.Project.gluPerspective;

/**
 *
 * @author Спок
 */
public class TestGame {

    public static void main(String[] args) throws LWJGLException {

        DisplayMode dplmd;
        dplmd = new DisplayMode(500, 500);
        Display.setDisplayMode(dplmd);
        Display.create();

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        //gluPerspective(60.0f, 1f, 1.0f, 200.0f);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_DEPTH_TEST);

        while (!Display.isCloseRequested()) {

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glClearColor(0.0f, 0.7f, 0.9f, 1.0f);

            glBegin(GL_QUADS);

            glColor3f(1, 0.5f, 0.1f);

            glVertex3f(0, 0, 0.1f);
            glVertex3f(0, 0.5f, 0.2f);
            glVertex3f(0.5f, 0.5f, 0.2f);
            glVertex3f(0.5f, 0, 0.1f);

            glEnd();

            Display.update();
            Display.sync(60);
        }
        Display.destroy();
        System.exit(0);

    }
}
