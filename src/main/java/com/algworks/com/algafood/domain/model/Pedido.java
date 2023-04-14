package com.algworks.com.algafood.domain.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time. OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "subTotal" , nullable = false)
    private BigDecimal subTotal;

    @Column(name = "taxaFrete" , nullable = false)
    private BigDecimal taxaFrete;

    @Column(name = "valorTotal" , nullable = false)
    private BigDecimal valorTotal;

    @CreationTimestamp
    @Column(name = "dataCriacao" , nullable = false)
    private  OffsetDateTime dataCriacao;

    @Column(name = "dataConfirmacao" , nullable = true)
    private  OffsetDateTime dataConfirmacao;

    @Column(name = "dataCancelamento" , nullable = true)
    private  OffsetDateTime dataCancelamento;

    @Column(name = "dataEntraga" , nullable = true)
    private  OffsetDateTime dataEntraga;

    @Column(name = "status", nullable = false, length = 10)
    private StatusPedido status;

    @Embedded
    private Endereco enderecoEntrega;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens = new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false)
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "usuario_cliente_id", nullable = false)
    private Usuario cliente;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(BigDecimal taxaFrete) {
        this.taxaFrete = taxaFrete;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public  OffsetDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao( OffsetDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public  OffsetDateTime getDataConfirmacao() {
        return dataConfirmacao;
    }

    public void setDataConfirmacao( OffsetDateTime dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }

    public  OffsetDateTime getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento( OffsetDateTime dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public  OffsetDateTime getDataEntraga() {
        return dataEntraga;
    }

    public void setDataEntraga( OffsetDateTime dataEntraga) {
        this.dataEntraga = dataEntraga;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;

        Pedido pedido = (Pedido) o;

        return id.equals(pedido.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", subTotal=" + subTotal +
                ", taxaFrete=" + taxaFrete +
                ", valorTotal=" + valorTotal +
                ", dataCriacao=" + dataCriacao +
                ", dataConfirmacao=" + dataConfirmacao +
                ", dataCancelamento=" + dataCancelamento +
                ", dataEntraga=" + dataEntraga +
                ", status=" + status +
                ", enderecoEntrega=" + enderecoEntrega +
                ", itens=" + itens +
                ", formaPagamento=" + formaPagamento +
                ", restaurante=" + restaurante +
                ", cliente=" + cliente +
                '}';
    }
}
