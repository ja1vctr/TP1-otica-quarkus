package br.unitins.topicos1.Service.pedido;

import java.time.LocalDateTime;

import br.unitins.topicos1.dto.pagamento.CartaoCreditoDTO;
import br.unitins.topicos1.dto.pagamento.PagamentoResponseDTO;
import br.unitins.topicos1.dto.pagamento.PixDTO;
import br.unitins.topicos1.model.pedido.Pagamento;
import br.unitins.topicos1.model.pedido.Pedido;
import br.unitins.topicos1.model.pedido.StatusPedido;
import br.unitins.topicos1.model.pedido.TipoPagamento;
import br.unitins.topicos1.repository.PagamentoRepository;
import br.unitins.topicos1.repository.PedidoRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PagamentoServiceImp implements PagamentoService {
    
    @Inject
    PagamentoRepository pagamentoRepository;
    
    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    PedidoService pedidoService;

    @Override
    @Transactional
    public PagamentoResponseDTO pagarPix(PixDTO dto) {
        Pedido pedido = pedidoRepository.findById(dto.idPedido());
        validarPedido(pedido);

        // Verificar se o pedido já tem um pagamento
        if (pedido.getPagamento() != null) {
            throw new ValidationException("Pagamento", "O pedido já possui um pagamento associado.");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setDataConfirmacaoPagamento(LocalDateTime.now());
        pagamento.setStatusPagamento(true);
        pagamento.setValor(pedido.getValorTotal());
        pagamento.setTipoPagamento(TipoPagamento.PIX);
        pagamento.setPedido(pedido);

        pedido.setStatusPedido(StatusPedido.PAGO);
        pedido.setPagamento(pagamento);
        pedidoRepository.persist(pedido);

        return PagamentoResponseDTO.valueOf(pagamento);
    }

    @Override
    @Transactional
    public PagamentoResponseDTO pagarCredito(CartaoCreditoDTO dto) {
        Pedido pedido = pedidoRepository.findById(dto.idPedido());
        validarPedido(pedido);

        // Verificar se o pedido já tem um pagamento
        if (pedido.getPagamento() != null) {
            throw new ValidationException("Pagamento", "O pedido já possui um pagamento associado.");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setDataConfirmacaoPagamento(LocalDateTime.now());
        pagamento.setStatusPagamento(true);
        pagamento.setValor(pedido.getValorTotal());
        pagamento.setTipoPagamento(TipoPagamento.CREDITO);
        pedido.setStatusPedido(StatusPedido.PAGO);
        
        // pagamentoRepository.persist(pagamento);
        // pagamentoRepository.flush();
        
        pagamento.setPedido(pedido);
        pedido.setPagamento(pagamento);
        // pedidoService.atualizaPedido(dto.idPedido(), pagamento.getId());

        pedidoRepository.persist(pedido);

        return PagamentoResponseDTO.valueOf(pagamento);
    }

    @Override
    public PagamentoResponseDTO findById(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id);
        validarPagamento(pagamento);
        return PagamentoResponseDTO.valueOf(pagamento);
    }

    @Override
    public Pagamento findByIdPedido(Long idPedido) {
        Pedido pedido = pedidoRepository.findById(idPedido);
        validarPedido(pedido);
        Pagamento pagamento = pedido.getPagamento();
        validarPagamento(pagamento);
        return pagamento;
    }

    /////////////
    public void validarPedido(Pedido pedido) {
        if(pedido == null) {
            throw new ValidationException("Pedido", "Pedido não encontrado");
        }
    }

    public void validarPagamento(Pagamento pagamento) {
        if(pagamento == null) {
            throw new ValidationException("Pagamento", "Pagamento não encontrado");
        }
    }
}

