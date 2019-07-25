/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

import static testgame.DirtBlock.SIDE_BACKWARD;
import static testgame.DirtBlock.SIDE_BOTTOM;
import static testgame.DirtBlock.SIDE_FORWARD;
import static testgame.DirtBlock.SIDE_LEFT;
import static testgame.DirtBlock.SIDE_NONE;
import static testgame.DirtBlock.SIDE_RIGTH;
import static testgame.DirtBlock.SIDE_TOP;

/**
 *
 * @author Спок
 */
public abstract class GameObject {

    protected boolean is_Prozrach = false;

    protected static int TexCount = 4;
    protected static final float texX1 = 1f / 3, texX2 = 2f / 3, TexHei = 1f / TexCount;

    protected float x = 0, y = 0f, z = 0, width = 0, heigth = 0, depth = 0, x_offset = 0, y_offset, z_offset;
    protected int LookSide = -1, touchSide = -1;
    public static final int SIDE_NONE = -1, SIDE_TOP = 1, SIDE_BOTTOM = 2, SIDE_RIGTH = 3, SIDE_LEFT = 4, SIDE_FORWARD = 5, SIDE_BACKWARD = 6;

    public GameObject(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public abstract void Draw();

    public static GameObject ObjectById(int id, float x, float y, float z) {
        switch (id) {
            case 1:
                return new DirtBlock(x, y, z);
            case 2:
                return new Tree(x, y, z);
            case 3:
                return new StoneBlock(x, y, z);
            case 4:
                return new BrickBlock(x, y, z);
        }

        return null;
    }

    public float IsLookAtMe(float cX, float cY, float cZ, float sX, float sY, float sZ) {
        /* float[][] vectors = {{x - cX, y - cY, z - cZ},
        {x + width - cX, y - cY, z - cZ},
        {x - cX, y - cY, z + depth - cZ},
        {x + width - cX, y - cY, z + depth - cZ},
        {x - cX, y + heigth - cY, z - cZ},
        {x + width - cX, y + heigth - cY, z - cZ},
        {x - cX, y + heigth - cY, z + depth - cZ},
        {x + width - cX, y + heigth - cY, z + depth - cZ}};

        for (int V = 0; V < vectors.length; V++) {
            
            

        }*/

        float finL = Float.MAX_VALUE;
        float bx = x - cX, by = y - cY, bz = z - cZ;

        {
            float h = (float) Math.hypot(sX, sZ);

            float K = h / sY, l = K * (by + heigth),
                    tX = sX / h * l, tZ = sZ / h * l;

            if ((tX >= bx && tX <= bx + width && tZ >= bz && tZ <= bz + depth) && (tX / sX >= 0 && by / sY > 0 && tZ / sZ > 0)) {
                float len = (float) Math.hypot(by + heigth, l);
                if (len < finL) {
                    finL = len;
                    LookSide = SIDE_TOP;
                }
            }
        }
        {
            float h = (float) Math.hypot(sX, sZ);

            float K = h / sY, l = K * (by),
                    tX = sX / h * l, tZ = sZ / h * l;

            if ((tX >= bx && tX <= bx + width && tZ >= bz && tZ <= bz + depth) && (tX / sX >= 0 && by / sY > 0 && tZ / sZ > 0)) {
                float len = (float) Math.hypot(by, l);
                if (len < finL) {
                    finL = len;
                    LookSide = SIDE_BOTTOM;
                }
            }
        }

        {
            float h = (float) Math.hypot(sX, sY);

            float K = h / sZ, l = K * (bz),
                    tX = sX / h * l, tY = sY / h * l;

            if ((tX >= bx && tX <= bx + width && tY >= by && tY <= by + heigth) && (tX / sX >= 0 && tY / sY > 0 && bz / sZ > 0)) {
                float len = (float) Math.hypot(bz, l);
                if (len < finL) {
                    finL = len;
                    LookSide = SIDE_BACKWARD;

                    //DecimalFormat dc = new DecimalFormat("#.###");
                    //System.err.println("backward");
                    //System.err.println("me at " + dc.format(bx) + ";" + dc.format(by) + ";" + dc.format(bz));
                    //System.err.println("touch at " + dc.format(tX) + ";" + dc.format(tY) + ";" + dc.format(bz));
                    //System.err.println("dist " + dc.format(len));
                }
            }
        }
        {
            float h = (float) Math.hypot(sX, sY);

            float K = h / sZ, l = K * (bz + depth),
                    tX = sX / h * l, tY = sY / h * l;

            if ((tX >= bx && tX <= bx + width && tY >= by && tY <= by + heigth) && (tX / sX >= 0 && tY / sY > 0 && bz / sZ > 0)) {
                float len = (float) Math.hypot(bz + depth, l);
                if (len < finL) {
                    finL = len;
                    LookSide = SIDE_FORWARD;

                    //DecimalFormat dc = new DecimalFormat("#.###");
                    //System.err.println("forward");
                    //System.err.println("me at " + dc.format(bx) + ";" + dc.format(by) + ";" + dc.format(bz));
                    //System.err.println("touch at " + dc.format(tX) + ";" + dc.format(tY) + ";" + dc.format(bz));
                    //System.err.println("dist " + dc.format(len));
                }
            }
        }
        {
            float h = (float) Math.hypot(sZ, sY);

            float K = h / sX, l = K * (bx),
                    tZ = sZ / h * l, tY = sY / h * l;

            if ((tZ >= bz && tZ <= bz + width && tY >= by && tY <= by + heigth) && (bx / sX >= 0 && tY / sY > 0 && tZ / sZ > 0)) {
                float len = (float) Math.hypot(bx, l);
                if (len < finL) {
                    finL = len;
                    LookSide = SIDE_LEFT;
                }
            }
        }
        {
            float h = (float) Math.hypot(sZ, sY);

            float K = h / sX, l = K * (bx + width),
                    tZ = sZ / h * l, tY = sY / h * l;

            if ((tZ >= bz && tZ <= bz + width && tY >= by && tY <= by + heigth) && (bx / sX >= 0 && tY / sY > 0 && tZ / sZ > 0)) {
                float len = (float) Math.hypot(bx + width, l);
                if (len < finL) {
                    finL = len;
                    LookSide = SIDE_RIGTH;
                }
            }
        }

        if (finL == Float.MAX_VALUE) {
            finL = -1;
            LookSide = SIDE_NONE;
        }

        return finL;
    }

    public float IsTouchMe(float cX, float cY, float cZ, float sX, float sY, float sZ) {

        float finL = Float.MAX_VALUE, vectLen = (float) Math.hypot(sX, Math.hypot(sY, sZ));
        float bx = x - cX, by = y - cY, bz = z - cZ;

        if (sY == 0) {
            if (by == -1 && bx <= 0 && bx >= -width && bz <= 0 && bz >= -depth) {
                finL = 0;
                touchSide = SIDE_TOP;
            }

        } else if (sX != 0 && sZ != 0) {
            {
                float h = (float) Math.hypot(sX, sZ);

                float K = h / sY, l = K * (by + heigth),
                        tX = sX / h * l, tZ = sZ / h * l;

                if ((tX >= bx && tX <= bx + width && tZ >= bz && tZ <= bz + depth) && (tX / sX >= 0 && by / sY > 0 && tZ / sZ > 0)) {
                    float len = (float) Math.hypot(by + heigth, l);
                    if (len < finL) {
                        finL = len;
                        touchSide = SIDE_TOP;
                    }
                }
            }
            {
                float h = (float) Math.hypot(sX, sZ);

                float K = h / sY, l = K * (by),
                        tX = sX / h * l, tZ = sZ / h * l;

                if ((tX >= bx && tX <= bx + width && tZ >= bz && tZ <= bz + depth) && (tX / sX >= 0 && by / sY > 0 && tZ / sZ > 0)) {
                    float len = (float) Math.hypot(by, l);
                    if (len < finL) {
                        finL = len;
                        touchSide = SIDE_BOTTOM;
                    }
                }
            }

            {
                float h = (float) Math.hypot(sX, sY);

                float K = h / sZ, l = K * (bz),
                        tX = sX / h * l, tY = sY / h * l;

                if ((tX >= bx && tX <= bx + width && tY >= by && tY <= by + heigth) && (tX / sX >= 0 && tY / sY > 0 && bz / sZ > 0)) {
                    float len = (float) Math.hypot(bz, l);
                    if (len < finL) {
                        finL = len;
                        touchSide = SIDE_BACKWARD;

                    }
                }
            }
            {
                float h = (float) Math.hypot(sX, sY);

                float K = h / sZ, l = K * (bz + depth),
                        tX = sX / h * l, tY = sY / h * l;

                if ((tX >= bx && tX <= bx + width && tY >= by && tY <= by + heigth) && (tX / sX >= 0 && tY / sY > 0 && bz / sZ > 0)) {
                    float len = (float) Math.hypot(bz + depth, l);
                    if (len < finL) {
                        finL = len;
                        touchSide = SIDE_FORWARD;

                    }
                }
            }
            {
                float h = (float) Math.hypot(sZ, sY);

                float K = h / sX, l = K * (bx),
                        tZ = sZ / h * l, tY = sY / h * l;

                if ((tZ >= bz && tZ <= bz + width && tY >= by && tY <= by + heigth) && (bx / sX >= 0 && tY / sY > 0 && tZ / sZ > 0)) {
                    float len = (float) Math.hypot(bx, l);
                    if (len < finL) {
                        finL = len;
                        touchSide = SIDE_LEFT;
                    }
                }
            }
            {
                float h = (float) Math.hypot(sZ, sY);

                float K = h / sX, l = K * (bx + width),
                        tZ = sZ / h * l, tY = sY / h * l;

                if ((tZ >= bz && tZ <= bz + width && tY >= by && tY <= by + heigth) && (bx / sX >= 0 && tY / sY > 0 && tZ / sZ > 0)) {
                    float len = (float) Math.hypot(bx + width, l);
                    if (len < finL) {
                        finL = len;
                        touchSide = SIDE_RIGTH;
                    }
                }
            }
        } else {
            {
                if (-by < finL && bx <= 0 && bx >= -width && bz <= 0 && bz >= -depth) {
                    finL = -by;
                    touchSide = SIDE_TOP;
                }
            }
           /* {
                if (by < finL && bx <= 0 && bx >= -width && bz <= 0 && bz >= -depth) {
                    finL = by;
                    touchSide = SIDE_BOTTOM;
                }

            }*/

        }

        if (finL == Float.MAX_VALUE || finL > vectLen) {
            finL = -1;
            touchSide  = SIDE_NONE;
        }

        return finL;
    }

    public int getLookSide() {
        return LookSide;
    }

    public int getTouchSide() {
        return touchSide;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getWidrh() {
        return width;
    }

    public float getHeigth() {
        return heigth;
    }

    public float getDepth() {
        return depth;
    }
}
