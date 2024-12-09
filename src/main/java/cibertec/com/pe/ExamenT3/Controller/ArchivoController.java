package cibertec.com.pe.ExamenT3.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
public class ArchivoController {
    private static final List<String> EXTENSIONES_VALIDAS = Arrays.asList("pdf", "png", "docx");
    private static final long TAMANO_MAXIMO_MB = 25 * 1024 * 1024; // 25MB en bytes

    // 1. Subida de múltiples archivos
    @PostMapping("/subir-multiples")
    public ResponseEntity<String> subirArchivosMultiples(@RequestParam("archivos") MultipartFile[] archivos) {
        for (MultipartFile archivo : archivos) {
            String extension = obtenerExtension(archivo.getOriginalFilename());
            if (!EXTENSIONES_VALIDAS.contains(extension)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Archivo no válido: " + archivo.getOriginalFilename() + ". Extensiones permitidas: " + EXTENSIONES_VALIDAS);
            }
        }
        return ResponseEntity.ok("Archivos subidos exitosamente.");
    }


}
