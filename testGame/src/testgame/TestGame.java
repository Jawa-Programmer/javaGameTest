/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.Project.*;

/**
 *
 * @author Спок
 */
public class TestGame {

    static final String sync_KEY = "KEY";

    static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 800;

    static int tex = 0;

    static float cX = 0, cY = 5, cZ = 0,
            rX = 0, rY = 0, rZ = -1, speed = 0.05f, vY = 0, vZ = 0, vX = 0, myHei = 1.7f;

    static boolean isOnGround = false;

    static final float NORMAL_SPEED = 0.05f, FAST_SPEED = 0.1f;

    static DecimalFormat dc = new DecimalFormat("#.###");

    static ArrayList<GameObject> blocks = new ArrayList<>();

    static int selcted_item = 1;

    static Thread physics = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TestGame.class.getName()).log(Level.SEVERE, null, ex);
                }

                GameObject br = null;
                float min = Float.MAX_VALUE;
                //Iterator<GameObject> iter = blocks.iterator();

                synchronized (sync_KEY) {
                    for (GameObject next : blocks) {
                        float dist = (next).IsTouchMe(cX, cY - myHei, cZ, vX, vY, vZ);
                        //  System.err.println(next.getTouchSide()+"\t"+dc.format(dist)+"\t"+dc.format(min));
                        if (next.getTouchSide() == GameObject.SIDE_TOP && dist >= 0 && dist <= min) {
                            if (br != null && dist == min) {
                                double d1 = Math.hypot(br.getY() - cY, Math.hypot(br.getX() - cX, br.getZ() - cZ)),
                                        d2 = Math.hypot(next.getY() - cY, Math.hypot(next.getX() - cX, next.getZ() - cZ));
                                if (d1 > d2) {
                                    continue;
                                }
                            }
                            min = dist;
                            br = next;
                        }
                    }
                }

                if (br != null) {
                    //  System.out.println(min);
                    cY = myHei + br.getHeigth() + br.getY();
                    vY = 0;
                    isOnGround = true;
                } else {
                    isOnGround = false;
                }
                if (!isOnGround) {
                    vY -= 0.006f;
                    cY += vY;

                    if (cY < -300) {
                        vY = 0;
                        cX = 0;
                        cZ = 0;
                        cY = 5;
                    }
                }

                /*if (cY < myHei) {
                    cY = myHei;
                    vY = 0;
                    isOnGround = true;
                } else if (cY > myHei) {
                    vY -= 0.006f;
                    isOnGround = false;
                }*/
                cX += vX;
                cZ += vZ;
            }
        }
    });

    //  public  static Point p = new Point(0, 0, 0);
    public static void main(String[] args) throws LWJGLException {

        DisplayMode dplmd;
        dplmd = new DisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        Display.setDisplayMode(dplmd);
        Display.setTitle("Test Game");
        Display.create();
        Mouse.setGrabbed(true);

        Random rand = new Random();

        int m = 5 + rand.nextInt(5);

        for (int i = 0; i < m; i++) {
            blocks.add(new Tree(10 - rand.nextInt(20), -0.01f, 10 - rand.nextInt(20)));
        }

        for (int XX = -10; XX < 11; XX++) {
            for (int ZZ = -10; ZZ < 11; ZZ++) {
                blocks.add(new DirtBlock(XX, -1, ZZ));
            }
        }
        for (int YY = -2; YY > -11; YY--) {
            for (int XX = -10; XX < 11; XX++) {
                for (int ZZ = -10; ZZ < 11; ZZ++) {
                    blocks.add(new StoneBlock(XX, YY, ZZ));
                }
            }
        }

        //blocks.add(new Brick(0, 1, 0));
        glLoadIdentity();
        gluPerspective(60, (float) SCREEN_WIDTH / SCREEN_HEIGHT, 0.1f, 200f);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_COLOR_MATERIAL);

        glClearStencil(0);
        glClearDepth(1.0f);
        glDepthFunc(GL_LEQUAL);
        try {
            tex = TextureLoader.loadTexture("resources/textures.png");
            DirtBlock.TEXI_ID = tex;
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
        FloatBuffer ambient = BufferUtils.createFloatBuffer(4);
        ambient.put(new float[]{0.2f, 0.2f, 0.2f, 1.0f});
        ambient.flip();

        FloatBuffer position = BufferUtils.createFloatBuffer(4);
        position.put(new float[]{0, 0, 1, 0,});
        position.flip();

        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);
        glLightModel(GL_LIGHT_MODEL_AMBIENT, ambient);
        glLight(GL_LIGHT0, GL_POSITION, position);*/

        // gluLookAt(0, 0, 0, 0, 0, -1, 0, 1, 0);
        physics.start();

        boolean mclick = true, mclick2 = true;
        while (!Display.isCloseRequested()) {

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glClearColor(0.0f, 0.7f, 0.9f, 0.0f);

            // glTranslatef(0, 0, -0.01f);
            /*glBegin(GL_QUADS);

            glColor3f(0.5f, 0.7f, 0.1f);
            glVertex3i(-10, 0, -10);
            glVertex3i(10, 0, -10);
            glVertex3i(10, 0, 10);
            glVertex3i(-10, 0, 10);

            glEnd();*/
            // //System.out.println("rX = " + rX + "; rZ = " + rZ);
            float normXZ = (float) Math.hypot(rZ, rX);

            if (Keyboard.isKeyDown(Keyboard.KEY_W) && Keyboard.isKeyDown(Keyboard.KEY_A)) {
                float tmpZ = rZ / normXZ - rX / normXZ;
                float tmpX = rX / normXZ + rZ / normXZ;
                float tmpN = (float) Math.hypot(tmpZ, tmpX);
                vZ = speed * tmpZ / tmpN;
                vX = speed * tmpX / tmpN;

            } else if (Keyboard.isKeyDown(Keyboard.KEY_W) && Keyboard.isKeyDown(Keyboard.KEY_D)) {

                float tmpZ = rZ / normXZ + rX / normXZ;
                float tmpX = rX / normXZ - rZ / normXZ;
                float tmpN = (float) Math.hypot(tmpZ, tmpX);
                vZ = speed * tmpZ / tmpN;
                vX = speed * tmpX / tmpN;

            } else if (Keyboard.isKeyDown(Keyboard.KEY_S) && Keyboard.isKeyDown(Keyboard.KEY_A)) {
                float tmpZ = rZ / normXZ + rX / normXZ;
                float tmpX = rX / normXZ - rZ / normXZ;
                float tmpN = (float) Math.hypot(tmpZ, tmpX);
                vZ = -speed * tmpZ / tmpN;
                vX = -speed * tmpX / tmpN;
            } else if (Keyboard.isKeyDown(Keyboard.KEY_S) && Keyboard.isKeyDown(Keyboard.KEY_D)) {
                float tmpZ = rZ / normXZ - rX / normXZ;
                float tmpX = rX / normXZ + rZ / normXZ;
                float tmpN = (float) Math.hypot(tmpZ, tmpX);
                vZ = -speed * tmpZ / tmpN;
                vX = -speed * tmpX / tmpN;
            } else if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
                vZ = speed * rZ / normXZ;
                vX = speed * rX / normXZ;
            } else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
                vZ = -speed * rZ / normXZ;
                vX = -speed * rX / normXZ;
            } else if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
                vZ = -speed * rX / normXZ;
                vX = speed * rZ / normXZ;
            } else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
                vZ = speed * rX / normXZ;
                vX = -speed * rZ / normXZ;
            } else {
                vX = 0;
                vZ = 0;
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                speed = FAST_SPEED;
            } else {
                speed = NORMAL_SPEED;
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && isOnGround) {
                vY = 0.2f;
                isOnGround = false;
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                break;
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_1)) {
                selcted_item = 1;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_2)) {
                selcted_item = 2;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_3)) {
                selcted_item = 3;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_4)) {
                selcted_item = 4;
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

                double sL = Math.hypot(rY, Math.hypot(rZ, rX));

                rX /= sL;
                rY /= sL;
                rZ /= sL;

                //  
            }

            if (Mouse.isButtonDown(0)) {
                if (mclick) {
                    mclick = false;
                    //System.err.println("button 0 is clicked");
                    GameObject br = null;
                    float min = Float.MAX_VALUE;
                    for (GameObject next : blocks) {
                        float dist = (next).IsLookAtMe(cX, cY, cZ, rX, rY, rZ);
                        if (dist > 0) {
                            //   //System.err.println("Looked at me. X = " + next.getX() + " Y = " + next.getY() + " Z = " + next.getZ() + " distantion = " + dist);
                        }
                        if (dist > 0 && dist <= min) {
                            if (br != null && dist == min) {
                                double d1 = Math.hypot(br.getY() - cY, Math.hypot(br.getX() - cX, br.getZ() - cZ)),
                                        d2 = Math.hypot(next.getY() - cY, Math.hypot(next.getX() - cX, next.getZ() - cZ));
                                if (d1 > d2) {
                                    continue;
                                }
                            }
                            min = dist;
                            br = next;
                        }

                    }
                    //System.err.println("-------------------------------------------------");
                    if (br != null) {
                        /*    System.err.println("Touch Side is " + dc.format(br.getLookSide()));
                        System.err.println("Deletet at " + dc.format(br.getX() - cX) + ";" + dc.format(br.getY() - cY) + ";" + dc.format(br.getZ() - cZ));
                        System.err.println("Dist to touch= " + min);
                         */
                        //     p = new Point(cX + rX * min, cY + rY * min, cZ + rZ * min);

                        synchronized (sync_KEY) {
                            blocks.remove(br);

                        }
                    }
                }
            } else {
                mclick = true;
            }
            if (Mouse.isButtonDown(1)) {
                if (mclick2) {
                    mclick2 = false;
                    //System.err.println("button 1 is clicked");
                    GameObject br = null;
                    float min = Float.MAX_VALUE;
                    for (GameObject next : blocks) {
                        float dist = (next).IsLookAtMe(cX, cY, cZ, rX, rY, rZ);
                        if (dist > 0 && dist <= min) {

                            if (br != null && dist == min) {
                                double d1 = Math.hypot(br.getY() - cY, Math.hypot(br.getX() - cX, br.getZ() - cZ)),
                                        d2 = Math.hypot(next.getY() - cY, Math.hypot(next.getX() - cX, next.getZ() - cZ));
                                if (d1 < d2) {
                                    continue;
                                }
                            }
                            min = dist;
                            br = next;
                        }

                    }

                    if (br != null) {
                        synchronized (sync_KEY) {
                            ////System.err.println("Dist to touch= " + min);
                            switch (br.getLookSide()) {
                                case DirtBlock.SIDE_TOP:
                                    // //System.err.println("Block touch Top");
                                    blocks.add(GameObject.ObjectById(selcted_item, br.getX(), br.getY() + br.getHeigth(), br.getZ()));
                                    break;
                                case DirtBlock.SIDE_BOTTOM:
                                    blocks.add(GameObject.ObjectById(selcted_item, br.getX(), br.getY() - br.getHeigth(), br.getZ()));
                                    break;
                                case DirtBlock.SIDE_FORWARD:
                                    blocks.add(GameObject.ObjectById(selcted_item, br.getX(), br.getY(), br.getZ() + br.getDepth()));
                                    break;
                                case DirtBlock.SIDE_BACKWARD:
                                    blocks.add(GameObject.ObjectById(selcted_item, br.getX(), br.getY(), br.getZ() - br.getDepth()));
                                    break;
                                case DirtBlock.SIDE_RIGTH:
                                    blocks.add(GameObject.ObjectById(selcted_item, br.getX() + br.getWidrh(), br.getY(), br.getZ()));
                                    break;
                                case DirtBlock.SIDE_LEFT:
                                    blocks.add(GameObject.ObjectById(selcted_item, br.getX() - br.getWidrh(), br.getY(), br.getZ()));
                                    break;
                            }
                        }
                    }
                }
            } else {
                mclick2 = true;
            }

            ready3D();

            glLoadIdentity();
            //glLight(GL_LIGHT0, GL_POSITION, position);
            gluLookAt(cX, cY, cZ, cX + rX, cY + rY, cZ + rZ, 0, 1, 0);
            // glTranslatef(cX, cY, cZ);
            for (GameObject bl : blocks) {

                bl.Draw();
            }
            // drawCircle(0, 3, 0, 2, 30);

            ready2D();

            glPointSize(5);
            glBegin(GL_LINES);
            glColor3f(1, 1, 1);
            glVertex2i(SCREEN_WIDTH / 2 - 10, SCREEN_HEIGHT / 2);
            glVertex2i(SCREEN_WIDTH / 2 + 10, SCREEN_HEIGHT / 2);

            glVertex2i(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2 - 10);
            glVertex2i(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2 + 10);

            glEnd();

            Display.update();
            Display.sync(60);

        }
        Display.destroy();
        System.exit(0);

    }

    static void ready3D() {
        glViewport(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        glMatrixMode(GL_PROJECTION);

        glLoadIdentity();
        gluPerspective(60, (float) SCREEN_WIDTH / SCREEN_HEIGHT, 0.1f, 200);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        glDepthFunc(GL_LEQUAL);
        glEnable(GL_DEPTH_TEST);

        glBindTexture(GL_TEXTURE_2D, tex);
    }

    static void ready2D() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        //gluOrtho2D(0.0f, SCREEN_WIDTH, SCREEN_HEIGHT, 0.0f);
        glOrtho(0.0f, SCREEN_WIDTH, SCREEN_HEIGHT, 0.0f, -1, 1);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glTranslatef(0.375f, 0.375f, 0.0f);

        glDisable(GL_DEPTH_TEST);

        glBindTexture(GL_TEXTURE_2D, 0);
    }

    static void drawCircle(float x, float y, float z, float r, int amountSegments) {

        glBindTexture(GL_TEXTURE_2D, 0);
        glColor3f(1, 0, 0);
        glBegin(GL_POLYGON);

        for (int i = 0; i < amountSegments; i++) {
            float angle = 2.0f * 3.1415926f * (float) (i) / (float) (amountSegments);
            float dx = r * (float) Math.cos(angle);
            float dy = r * (float) Math.sin(angle);
            glVertex3f(x + dx, y + dy, z);
        }
        glEnd();
    }

}
