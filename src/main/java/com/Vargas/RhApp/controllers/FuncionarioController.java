package com.Vargas.RhApp.controllers;


import com.Vargas.RhApp.Models.Dependentes;
import com.Vargas.RhApp.Models.Funcionario;
import com.Vargas.RhApp.repository.DependentesRepository;
import com.Vargas.RhApp.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository fr;
    @Autowired
    private DependentesRepository dr;
    @RequestMapping(value = "/cadastrarFuncionario", method = RequestMethod.GET)
    public String form(){
        return "funcionario/formFuncionario";
    }

    @RequestMapping(value = "/cadastrarFuncionario", method = RequestMethod.POST)
    public String form(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Erro:" + result.hasErrors());
            return "redirect:/cadastrarFuncionario";
        }
        if (funcionario == null) {
            attributes.addFlashAttribute("mensagem", "Os Campos não Podem ser Vazios");
            return "redirect:/cadastrarFuncionario";
        }
        fr.save(funcionario);
        attributes.addFlashAttribute("mensagem", "Sucesso na cadastração do Funcionario");
        return "redirect:/cadastrarFuncionario";
    }

    @RequestMapping("/funcionarios" )
    public ModelAndView listaFuncionario(){
        ModelAndView mv = new ModelAndView("funcionario/listarFuncionario");
        Iterable<Funcionario> funcionarios = fr.findAll();
        mv.addObject("funcionarios",funcionarios);
        return mv;
    }


    @RequestMapping("/dependentes/{id}")
    public ModelAndView dependentes(@PathVariable("id") long id){
        Funcionario funcionario = fr.findById(id);
        ModelAndView mv = new ModelAndView("funcionario/dependentes");
        mv.addObject("funcionario", funcionario);
        Iterable<Dependentes> dependentes = dr.findByFuncionario(funcionario);
        mv.addObject("dependentes", dependentes);
        return mv;
    }

    @RequestMapping(value = "/dependentes/{id}", method = RequestMethod.POST)
    public String dependentesPost(@PathVariable("id") long id , Dependentes dependentes, BindingResult result, RedirectAttributes attributes){

        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Erro : "+ result.hasErrors());
            return "redirect:/dependentes/{id}";
        }

        if (dr.findByCpf(dependentes.getCpf())!= null){
            attributes.addFlashAttribute("mensagem_erro", "Esse dependente ja existe no sistema " );
            return "redirect:/dependentes/{id}";
        }

        Funcionario funcionario = fr.findById(id);
        dependentes.setFuncionario(funcionario);
        dr.save(dependentes);
        attributes.addFlashAttribute("mensagem", "Dependente cadastrado com sucesso");
        return "redirect:/dependentes/{id}";
    }
    @RequestMapping(value = "/deletarFuncionario")

    public String deletarFuncionario(long id){
        Funcionario funcionario = fr.findById(id);
        fr.delete(funcionario);
        return "redirect:/funcionarios";

    }

    @RequestMapping(value = "/editarFuncionario", method = RequestMethod.GET)
    public ModelAndView editarFuncionario(long id){
        Funcionario funcionario = fr.findById(id);
        ModelAndView mv = new ModelAndView("funcionario/updateFuncionario");
        mv.addObject("funcionario", funcionario);
        return mv;
    }

    @RequestMapping(value = "/editarFuncionario", method = RequestMethod.POST)
    public String updateFuncionario(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attributes){
        fr.save(funcionario);
        attributes.addFlashAttribute("mensagem", "Funcionario Atualizado com sucesso");
        long idLong = funcionario.getId();
        String id = "" + idLong;

        return "redirect:/dependententes/" + id;
    }


    @RequestMapping(value = "/deletarCandidato")
    public String deletarFuncionarios (String cpf){
        Dependentes dependentes = dr.findByCpf(cpf);
        dr.delete(dependentes);
        Funcionario funcionario = dependentes.getFuncionario();
        String codigo = "" + funcionario.getId();
        return "redirect:/dependentes/" + codigo;
    }





}
