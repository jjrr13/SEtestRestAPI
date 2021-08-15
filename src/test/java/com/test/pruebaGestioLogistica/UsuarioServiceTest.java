package com.test.pruebaGestioLogistica;

import com.test.pruebaGestioLogistica.entities.Usuario;
import com.test.pruebaGestioLogistica.repository.IUsuarioRepository;
import com.test.pruebaGestioLogistica.services.IServiceUsuario;
import com.test.pruebaGestioLogistica.services.impl.UsuarioServiceImple;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.util.Arrays;
import java.util.Collections;

@RunWith(SpringRunner.class)
public class UsuarioServiceTest {

    private IServiceUsuario iServiceUsuario;
    private IUsuarioRepository iUsuarioRepository;

    @Test
    public void testCargarListaVacia(){
        iUsuarioRepository = Mockito.mock(IUsuarioRepository.class);
        iServiceUsuario = new UsuarioServiceImple(iUsuarioRepository);
        Mockito.when(iUsuarioRepository.findAll()).thenReturn(Collections.emptyList());
        Assert.assertTrue(iServiceUsuario.listAll().isEmpty());
    }

    @Test
    public void testCargarLista(){
        iUsuarioRepository = Mockito.mock(IUsuarioRepository.class);
        iServiceUsuario = new UsuarioServiceImple(iUsuarioRepository);
        Mockito.when(iUsuarioRepository.findAll()).thenReturn(Arrays.asList(new Usuario()));
        Assert.assertEquals(1,iServiceUsuario.listAll().size());
    }


}
