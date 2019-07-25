/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex3f;
import static testgame.GameObject.texX1;

/**
 *
 * @author Спок
 */
public class BrickBlock extends GameObject { 
    
    public static int TEXI_ID = 0;
    
    final int texN=3;


   
    public BrickBlock(float x, float y, float z) {
        super(x, y, z);
        width = 1;
        heigth = 1;
        depth = 1;
    }

    @Override
    public void Draw() {

        //glBindTexture(GL_TEXTURE_2D, TEXI_ID);
        glBegin(GL_QUADS);

        glColor3f(1, 1, 1);

//top
        glTexCoord2f(texX1, TexHei * (texN ));
        glVertex3f(x, y + heigth, z);
        glTexCoord2f(texX2, TexHei * (texN ));
        glVertex3f(x + width, y + heigth, z);
        glTexCoord2f(texX2, TexHei * (texN +1));
        glVertex3f(x + width, y + heigth, z + depth);
        glTexCoord2f(texX1, TexHei * (texN +1));
        glVertex3f(x, y + heigth, z + depth);
//bottom

        glTexCoord2f(texX2, TexHei * (texN ));
        glVertex3f(x, y, z);
        glTexCoord2f(1, TexHei * (texN ));
        glVertex3f(x + width, y, z);
        glTexCoord2f(1, TexHei * (texN+1 ));
        glVertex3f(x + width, y, z + depth);
        glTexCoord2f(texX2, TexHei * (texN +1));
        glVertex3f(x, y, z + depth);

//right
        glTexCoord2f(0, TexHei * (texN +1));
        glVertex3f(x + width, y, z);
        glTexCoord2f(0, TexHei * (texN ));
        glVertex3f(x + width, y + heigth, z);
        glTexCoord2f(texX1, TexHei * (texN ));
        glVertex3f(x + width, y + heigth, z + depth);
        glTexCoord2f(texX1, TexHei * (texN +1));
        glVertex3f(x + width, y, z + depth);

//left
        glTexCoord2f(0, TexHei * (texN +1));
        glVertex3f(x, y, z);
        glTexCoord2f(0, TexHei * (texN ));
        glVertex3f(x, y + heigth, z);
        glTexCoord2f(texX1, TexHei * (texN ));
        glVertex3f(x, y + heigth, z + depth);
        glTexCoord2f(texX1, TexHei * (texN +1));
        glVertex3f(x, y, z + depth);

//forward
        glTexCoord2f(0, TexHei * (texN +1));
        glVertex3f(x, y, z);
        glTexCoord2f(texX1, TexHei * (texN+1 ));
        glVertex3f(x + width, y, z);
        glTexCoord2f(texX1, TexHei * (texN ));
        glVertex3f(x + width, y + heigth, z);
        glTexCoord2f(0, TexHei * (texN ));
        glVertex3f(x, y + heigth, z);

//backward
        glTexCoord2f(0, TexHei * (texN +1));
        glVertex3f(x, y, z + depth);
        glTexCoord2f(texX1, TexHei * (texN+1 ));
        glVertex3f(x + width, y, z + depth);
        glTexCoord2f(texX1, TexHei * (texN ));
        glVertex3f(x + width, y + heigth, z + depth);
        glTexCoord2f(0, TexHei * (texN ));
        glVertex3f(x, y + heigth, z + depth);

        
        glEnd();

    }

    
}