package br.com.abreu.taskmanager.api.dto;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IdObjeto {
    private String idPessoa;
    private UUID idVenda;
    private Long idQualquer;
}
