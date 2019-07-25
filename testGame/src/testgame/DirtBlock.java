/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Спок
 */
public class DirtBlock extends GameObject {

    public static int TEXI_ID = 0;
    
    final int texN=0;


   
    public DirtBlock(float x, float y, float z) {
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

        /* 
//top
        glColor3f(0.5f, 0.7f, 0.1f);

        glVertex3f(x, y + heigth, z);
        glVertex3f(x + width, y + heigth, z);
        glVertex3f(x + width, y + heigth, z + depth);
        glVertex3f(x, y + heigth, z + depth);
//bottom

        glColor3f(0.65f, 0.45f, 0.05f);

        glVertex3f(x, y, z);
        glVertex3f(x + width, y, z);
        glVertex3f(x + width, y, z + depth);
        glVertex3f(x, y, z + depth);

        glColor3f(0.7f, 0.5f, 0.1f);

//right
        glVertex3f(x + width, y, z);
        glVertex3f(x + width, y + heigth * 0.7f, z);
        glVertex3f(x + width, y + heigth * 0.7f, z + depth);
        glVertex3f(x + width, y, z + depth);

//left
        glVertex3f(x, y, z);
        glVertex3f(x, y + heigth * 0.7f, z);
        glVertex3f(x, y + heigth * 0.7f, z + depth);
        glVertex3f(x, y, z + depth);

//forward
        glVertex3f(x, y, z);
        glVertex3f(x + width, y, z);
        glVertex3f(x + width, y + heigth * 0.7f, z);
        glVertex3f(x, y + heigth * 0.7f, z);

//backward
        glVertex3f(x, y, z + depth);
        glVertex3f(x + width, y, z + depth);
        glVertex3f(x + width, y + heigth * 0.7f, z + depth);
        glVertex3f(x, y + heigth * 0.7f, z + depth);

        glColor3f(0.5f, 0.7f, 0.1f);
//right - G
        glVertex3f(x + width, y + heigth * 0.7f, z);
        glVertex3f(x + width, y + heigth, z);
        glVertex3f(x + width, y + heigth, z + depth);
        glVertex3f(x + width, y + heigth * 0.7f, z + depth);

//left - G
        glVertex3f(x, y + heigth * 0.7f, z);
        glVertex3f(x, y + heigth, z);
        glVertex3f(x, y + heigth, z + depth);
        glVertex3f(x, y + heigth * 0.7f, z + depth);

//forward - G
        glVertex3f(x, y + heigth * 0.7f, z);
        glVertex3f(x + width , y+ heigth * 0.7f, z);
        glVertex3f(x + width, y + heigth, z);
        glVertex3f(x, y + heigth, z);

//backward - G
        glVertex3f(x, y + heigth * 0.7f, z + depth);
        glVertex3f(x + width, y + heigth * 0.7f, z + depth);
        glVertex3f(x + width, y + heigth, z + depth);
        glVertex3f(x, y + heigth, z + depth);
         */
        glEnd();

    }

    
}
