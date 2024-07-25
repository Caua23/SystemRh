package com.Vargas.RhApp.controllers;


import com.Vargas.RhApp.repository.CandidatoRepository;
import com.Vargas.RhApp.repository.VagaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.Vargas.RhApp.Models.Vaga;
import com.Vargas.RhApp.Models.Candidatos;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VagaController {
    @Autowired
    private VagaRepository vr;
    @Autowired
    private CandidatoRepository cr;

    //Cadastro de vaga
    @RequestMapping(value = "/CadastroDeVaga", method = RequestMethod.GET)
    public String form(){

        return "vaga/formVaga.html";
    }

    @RequestMapping(value = "/CadastroDeVaga", method = RequestMethod.POST)
    public String form(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes){

        if (result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os campos...");
            return "redirect:/CadastroDeVaga";
        }
        vr.save(vaga);
        long codigoLong = vaga.getCodigo();
        String codigo = "" + codigoLong;
        attributes.addFlashAttribute("mensagem", "Vaga cadastrada com sucesso");
        return "redirect:/" + codigo;
    }



    //Lista de vagas
    @RequestMapping("/vagas")
    public ModelAndView listaVagas(){
        ModelAndView mv = new ModelAndView("vaga/listaVaga");
        Iterable<Vaga>vagas = vr.findAll();
        mv.addObject("vagas",vagas);

        return mv;
    }
    @RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
    public ModelAndView detalhesVagas(@PathVariable("codigo") long codigo){
        Vaga vaga = vr.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("vaga/detalhesVaga");
        mv.addObject("vaga", vaga);
        Iterable<Candidatos>candidatos = cr.findByVaga(vaga);
        mv.addObject("candidatos",candidatos);
        return mv;
    }
    //delete de vaga
    @RequestMapping("/deletarVaga")
    public String deleteVaga(long codigo){
        Vaga vaga = vr.findByCodigo(codigo);
        vr.delete(vaga);
        return "redirect:/vagas";
    }
    //cadastramento de candidato
    @RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
    public String detalhesVagaPost(@PathVariable("codigo") long codigo, @Valid Candidatos candidatos,BindingResult result , RedirectAttributes attributes){
        if (result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Algo deu Errado:" + result );
            return "redirect:/{codigo}";
        }
        if (cr.findByRg(candidatos.getRg()) != null){
            attributes.addFlashAttribute("mensagem_erro", "Já existe esse rg no sistema");
            return "redirect:/{codigo}";
        }

        Vaga vaga = vr.findByCodigo(codigo);
        candidatos.setVaga(vaga);
        cr.save(candidatos);
        attributes.addFlashAttribute("mensagem","Candidato adicionado com sucesso");
        return "redirect:/{codigo}";
    }

    //delete de usuario pelo rg
    @RequestMapping("/deletarCandidato")
    public String deleteCandidato(String rg){
        Candidatos candidatos = cr.findByRg(rg);
        Vaga vaga = candidatos.getVaga();
        String codigo = "" + vaga.getCodigo();
        cr.delete(candidatos);

        return "redirect:/" + codigo;
    }

    //Atualizam a vaga
    //formulario de edição de vaga

    @RequestMapping(value = "/editar-vaga", method = RequestMethod.GET)
    public ModelAndView editarVaga(long codigo){
        Vaga vaga  = vr.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("vaga/update-vaga");
        mv.addObject("vaga", vaga);
        return mv;
    }
    @RequestMapping(value = "/editar-vaga", method = RequestMethod.POST)
    public String updateVaga(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes){
        vr.save(vaga);
        attributes.addFlashAttribute("Sucess", "Vaga alterada com sucesso truta");

        long codigoLong = vaga.getCodigo();
        String codigo = "" + codigoLong;

        return "redirect:/" + codigo;
    }

}

