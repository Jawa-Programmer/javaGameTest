/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

import java.util.ArrayList;
import java.util.Random;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.Project.gluLookAt;
import static org.lwjgl.util.glu.Project.gluPerspective;

/**
 *
 * @author Спок
 */
public class TestGame {

    static float cX = 0, cY = 1, cZ = 0,
            rX = 0, rY = 0, rZ = -1, speed = 0.05f, vY = 0, myHei = 0.5f;

    public static void main(String[] args) throws LWJGLException {

        ArrayList<Tree> blocks = new ArrayList<>();

        Mouse.setGrabbed(true);
        Random rand = new Random();

        int m = 20 + rand.nextInt(20);
        for (int i = 0; i < m; i++) {
            if(rand.nextBoolean()){
            blocks.add(new Tree(10 - rand.nextFloat() * 20, -0.01f, 10 - rand.nextFloat() * 20));
            }
            else
            {            
            blocks.add(new BigTree(10 - rand.nextFloat() * 20, -0.01f, 10 - rand.nextFloat() * 20));}
        }

        DisplayMode dplmd;
        dplmd = new DisplayMode(800, 800);
        Display.setDisplayMode(dplmd);
        Display.setTitle("Test Game");
        Display.create();

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(60.0f, 1f, 0.01f, 200.0f);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_DEPTH_TEST);

        //glTranslatef(0, 0, -2);
        // gluLookAt(0, 0, 0, 0, 0, -1, 0, 1, 0);
        while (!Display.isCloseRequested()) {

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glClearColor(0.0f, 0.7f, 0.9f, 1.0f);

            // glTranslatef(0, 0, -0.01f);
            for (Tree bl : blocks) {
                bl.Draw();
            }

            glBegin(GL_QUADS);

            glColor3f(0.5f, 0.7f, 0.1f);
            glVertex3i(-10, 0, -10);
            glVertex3i(10, 0, -10);
            glVertex3i(10, 0, 10);
            glVertex3i(-10, 0, 10);

            glEnd();

            // System.out.println("rX = " + rX + "; rZ = " + rZ);
            if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
                cZ += speed * rZ;
                cX += speed * rX;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
                cZ -= speed * rZ;
                cX -= speed * rX;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
                cZ -= speed * rX;
                cX += speed * rZ;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
                cZ += speed * rX;
                cX -= speed * rZ;
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && cY == myHei) {
                vY = 0.2f;
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                break;
            }

            if (Mouse.isGrabbed()) {
                float xr = Mouse.getDX() / 400f, yr = -Mouse.getDY() / 400f;

                float trX = rX * (float) Math.cos(xr) - rZ * (float) Math.sin(xr),
                        trZ = rX * (float) Math.sin(xr) + rZ * (float) Math.cos(xr);

                rX = trX;
                rZ = trZ;

                float F = (float) Math.hypot(rX, rZ);

                float trY = rY * (float) Math.cos(yr) - F * (float) Math.sin(yr),
                        trF = rY * (float) Math.sin(yr) + F * (float) Math.cos(yr);

                rY = trY;

                //  
            }

            if (cY < myHei) {
                cY = myHei;
                vY = 0;
            } else if (cY > myHei) {
                vY -= 0.01f;
            }
            cY += vY;

            glLoadIdentity();
            gluLookAt(cX, cY, cZ, cX + rX, cY + rY, cZ + rZ, 0, 1, 0);

            Display.update();
            Display.sync(60);
        }
        Display.destroy();
        System.exit(0);

    }
}
