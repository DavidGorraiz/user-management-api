package com.davidgorraiz.userapi.service;

import com.davidgorraiz.userapi.dto.RoleDTO;
import com.davidgorraiz.userapi.dto.RoleUpdateDTO;
import com.davidgorraiz.userapi.exceptions.RoleNotFoundException;
import com.davidgorraiz.userapi.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    // Arrange
    List<RoleDTO> roles = List.of(
            new RoleDTO(1L, "Admin"),
            new RoleDTO(2L, "User")
    );

    @Test
    void shouldReturnAllRoles(){
        when(roleRepository.getAll()).thenReturn(roles);

        // Act
        List<RoleDTO> result = roleService.getAll();
        System.out.println(result);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Admin", result.get(0).name());

        verify(roleRepository).getAll();
    }
    @Test
    void shouldReturnRoleById(){
        when(roleRepository.getById(roles.get(1).id())).thenReturn(Optional.ofNullable((roles.get(1))));

        RoleDTO result = roleService.getById(roles.get(1).id());
        System.out.println(result);

        assertThat(result).isNotNull();
        assertEquals(result.name(), roles.get(1).name());

        verify(roleRepository).getById(roles.get(1).id());
    }
    @Test
    void shouldReturnErrorNotFound(){
        long id = 999L;
        when(roleRepository.getById(id)).thenReturn(Optional.empty());

        RoleNotFoundException ex = assertThrows(RoleNotFoundException.class,
                () -> roleService.getById(id));
        System.out.println(ex);

        assertEquals("Role with id: 999 not found", ex.getMessage());
    }
    @Test
    void shouldCreateRole(){
        // Simular datos de nuevo rol
        RoleDTO roleToCreate = new RoleDTO(1L, "NEW ROLE");

        // Simular lo que pasa cuando se llama al reposiorio
        when(roleRepository.createRole(roleToCreate)).thenReturn(roleToCreate);

        // Probar el metodo en el servicio
        RoleDTO result = roleService.createRole(roleToCreate);

        // verificar que los datos coincidan
        assertThat(roleToCreate.name()).isEqualTo(result.name());

        // verificar que se llame al repo desde el servicio
        verify(roleRepository).createRole(roleToCreate);
    }
    @Test
    void shouldUpdateRole(){
        // Simmular datos para actualizar
        RoleDTO roleFound = roles.get(1);
        RoleUpdateDTO roleUpdate = new RoleUpdateDTO("ROLE UPDATED");
        RoleDTO roleUpdated = new RoleDTO(roleFound.id(), roleUpdate.name());

        // Simulamos lo que devolvera el repositorio
        when(roleRepository.getById(roleFound.id())).thenReturn(Optional.of(roleFound));
        when(roleRepository.updateRole(roleFound.id(), roleUpdate)).thenReturn(roleUpdated);

        // Llamamos al metodo que queremos probar y se guarda en un resultado
        RoleDTO result = roleService.updateRole(roleFound.id(), roleUpdate);

        // Verificamos que los datos coincidan
        assertThat(roleUpdated).isEqualTo(result);

        // Verificar que se llamo el repositorio
        verify(roleRepository).getById(roleFound.id());
        verify(roleRepository).updateRole(roleFound.id(), roleUpdate);
    }
    @Test
    void shouldDeleteRole(){
        // Simular datos a borrar
        RoleDTO roleToDelete = roles.get(1);

        // Simular lo que devuelve la capa repositorio
        when(roleRepository.getById(roleToDelete.id())).thenReturn(Optional.of(roleToDelete));
        when(roleRepository.deleteRole(roleToDelete.id())).thenReturn(roleToDelete);

        // Probamos el metodo y lo guardamos en el resultado
        RoleDTO result = roleService.deleteRole(roleToDelete.id());

        // Verificar que los datos coinciden
        assertThat(roleToDelete).isEqualTo(result);

        // Verificar que se llama a los metodos del repositorio
        verify(roleRepository).getById(roleToDelete.id());
        verify(roleRepository).deleteRole(roleToDelete.id());
    }
}
