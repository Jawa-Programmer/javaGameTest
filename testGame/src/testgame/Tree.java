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
public class Tree extends GameObject {

    protected static final int texN = 2;

    public Tree(float x, float y, float z) {
        super(x, y, z);
        width = 0.75f;
        heigth = 1f;
        depth = 0.75f;
        x_offset = 0.25f / 2;
        z_offset = 0.25f / 2;
        is_Prozrach=true;
    }

    public void Draw() {
/*
        glBegin(GL_QUADS);

        glColor4f(1, 1, 1,1);

        glTexCoord2f(0, TexHei * (texN + 1));
        glVertex3f(x + x_offset, y, z + z_offset + depth / 2);
                glTexCoord2f(texX1, TexHei * (texN + 1));
        glVertex3f(x + x_offset + width, y, z + z_offset + depth / 2);        
        glTexCoord2f(texX1, TexHei * (texN));
        glVertex3f(x + x_offset + width, y+heigth, z + z_offset + depth / 2);        
        glTexCoord2f(0, TexHei * (texN));
        glVertex3f(x + x_offset , y+heigth, z + z_offset + depth / 2);

        glEnd();*/

        glBegin(GL_TRIANGLES);

        glColor4f(1, 1, 1,1);

        glTexCoord2f(0, TexHei * (texN + 1));
        glVertex3f(x + x_offset, y, z + z_offset + depth / 2);
                glTexCoord2f(texX1, TexHei * (texN + 1));
        glVertex3f(x + x_offset + width, y, z + z_offset + depth / 2);        
        glTexCoord2f(texX1/2, TexHei * (texN));
        glVertex3f(x + x_offset + width/2, y+heigth, z + z_offset + depth / 2);  

        glEnd();
    }

}
