package org.Main.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@Setter
@Getter

public class Producer {
    private List<Client> clients;
}
