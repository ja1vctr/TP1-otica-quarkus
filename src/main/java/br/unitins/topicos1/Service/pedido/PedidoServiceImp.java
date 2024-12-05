package br.unitins.topicos1.Service.pedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.pedido.ItemPedidoDTO;
import br.unitins.topicos1.dto.pedido.PedidoDTO;
import br.unitins.topicos1.dto.pedido.PedidoResponseDTO;
import br.unitins.topicos1.model.Produto;
import br.unitins.topicos1.model.Status;
import br.unitins.topicos1.model.pedido.Endereco;
import br.unitins.topicos1.model.pedido.ItemPedido;
import br.unitins.topicos1.model.pedido.Pagamento;
import br.unitins.topicos1.model.pedido.Pedido;
import br.unitins.topicos1.model.pedido.StatusPedido;
import br.unitins.topicos1.model.user.Perfil;
import br.unitins.topicos1.model.user.Usuario;
import br.unitins.topicos1.repository.EnderecoRepository;
import br.unitins.topicos1.repository.PagamentoRepository;
import br.unitins.topicos1.repository.PedidoRepository;
import br.unitins.topicos1.repository.ProdutoRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PedidoServiceImp implements PedidoService{

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    ProdutoRepository produtoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    EnderecoRepository enderecoRepository;
    
    @Inject
    PagamentoRepository pagamentoRepository;

    @Override
    @Transactional
    public PedidoResponseDTO create(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());

        if(pedidoDTO.listaItemPedido().isEmpty() && pedidoDTO.listaItemPedido() == null) {
            throw new ValidationException("ListaItensPedido","Lista de itens vazia");
        }
        for(ItemPedidoDTO item : pedidoDTO.listaItemPedido()) {
            if(item.quantidade() == 0) {
                throw new ValidationException("ListaItensPedido","Quantidade de itens inválida");
            }
        }
        
        pedido.setValorTotal(pedidoDTO.valorTotal());

        Usuario usuario = usuarioRepository.findById(pedidoDTO.usuario());
        verificaUsuario(usuario);
        pedido.setUsuario(usuario);

        Endereco endereco = enderecoRepository.findById(pedidoDTO.enderecoEntrega());
        pedido.setEnderecoEntrega(endereco);

        pedido.setStatusPedido(StatusPedido.PENDENTE);
        
        final double[] somaItens = {0.0};

        List<ItemPedido> itensPedido = pedidoDTO.listaItemPedido().stream().map(item -> {
            Produto produto = produtoRepository.findById(item.idProduto());
            
            verificaProduto(produto);
            
            produto.setQuantidade(produto.getQuantidade() - item.quantidade());

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(item.quantidade());
            itemPedido.setPrecoUnitario(item.precoUnitario());
            itemPedido.setProduto(produto);

            somaItens[0] += item.quantidade() * item.precoUnitario();
            
            return itemPedido;
        }).collect(Collectors.toList());
        
        if(pedido.getValorTotal() != somaItens[0]) {
            throw new ValidationException("ValorTotal","Valor total inválido");
        }

        pedido.setListaItensPedido(itensPedido);

        pedidoRepository.persist(pedido);

        return PedidoResponseDTO.valueOf(pedido);
    }

    public List<PedidoResponseDTO> getPedidosByUsuario(Long usuarioId) {
        List<Pedido> pedidos = pedidoRepository.findByUsuarioId(usuarioId);
        return pedidos.stream().map(PedidoResponseDTO::valueOf).collect(Collectors.toList());
    }
    
    @Override
    public PedidoResponseDTO findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id);
        verificaPedido(pedido);

        return PedidoResponseDTO.valueOf(pedidoRepository.findById(id));
    }

    @Override
    public List<PedidoResponseDTO> findByUserId(Long id) {
        List<Pedido> pedidos = pedidoRepository.findByUsuarioId(id);
    return pedidos.stream().map(PedidoResponseDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    public PedidoResponseDTO atualizaPedido(Long id, Long pagamentoId) {
        Pedido pedido = pedidoRepository.findById(id);
        verificaPedido(pedido);

        Pagamento pagamento = pagamentoRepository.findById(pagamentoId);

        pedido.setPagamento(pagamento);

        return PedidoResponseDTO.valueOf(pedido);
    }

    ///////////////////
    public void verificaPedido(Pedido pedido) {
        if ( pedido == null)
            throw new ValidationException("pedido", "Pedido não encontrado");
    }


    public void verificaUsuario(Usuario usuario) {
        if ( usuario.getPerfil() == Perfil.ADM)
            throw new ValidationException("usuario", "Usuario ADM não permitido fazer pedidos");
    }

    public void verificaProduto(Produto produto) {
        if ( produto == null)
            throw new ValidationException("produto", "Produto não encontrado");
    }

        
}
