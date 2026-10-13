package security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtUtils   {
    //Authorization   - > Bearer  <token>

    public String getJwtHeader(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken !=null && bearerToken.startsWith("Barear ")){
            return bearerToken.substring(7);
        }
        return null ;
    }
}
