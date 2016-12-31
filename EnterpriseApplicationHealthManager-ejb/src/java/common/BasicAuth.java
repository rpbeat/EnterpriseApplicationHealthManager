/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Emanuel Lopes
 */
public class BasicAuth {

    public static String decodeUsername(String auth) {
        String authLogin[] = decode(auth);
        return authLogin[0];
    }

    public static String decodePassword(String auth) {
        String authLogin[] = decode(auth);
        return authLogin[1];
    }

    private static String[] decode(String auth) {
        auth = auth.replaceFirst("[B|b]asic ", "");

        //Decode the Base64 into byte[]
        byte[] decodedBytes = DatatypeConverter.parseBase64Binary(auth);

        //If the decode fails in any case
        if (decodedBytes == null || decodedBytes.length == 0) {
            return null;
        }

        return new String(decodedBytes).split(":", 2);
    }
}
